import mysql.connector
import configparser

from mysql.connector import errorcode

from log import log


class db:
    cnx = mysql.connector
    config_obj = configparser.ConfigParser()
    config_obj.read("config.ini")
    sql_config = config_obj["sql"]
    cursor = cnx

    # sql_query = ""

    def __init__(self):
        self.cnx = mysql.connector.connect(user=self.sql_config['user'], password=self.sql_config['pass'],
                                           host=self.sql_config['ip'],
                                           )
        self.cursor = self.cnx.cursor()
        try:
            self.cursor.execute("USE %s" % self.sql_config["datebase"])
        except mysql.connector.Error as err:
            log(err)
            if err.errno == errorcode.ER_BAD_DB_ERROR:
                self.Create_Database()
                self.cnx.database = self.sql_config["datebase"]
            else:
                log(err)

    def GET(self):
        print("Todo")



    def dissconnect(self):
        self.cnx.close()