package com.example.test_nfc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    protected lateinit var login : String
    protected lateinit var pass:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sign = findViewById(R.id.login) as Button
        val login = findViewById(R.id.editTextTextPersonName) as EditText
        val pass= findViewById(R.id.editTextTextPassword) as EditText
        val intent = Intent(this,enterscreen::class.java)

        //this.login = read(settings.login)
        //this.pass =read(settings.pass)



        sign.setOnClickListener {


            val file:String = "p.txt"
            val data:String = pass.text.toString()
            val fileOutputStream: FileOutputStream
            try {
                fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            }catch (e: Exception){
                e.printStackTrace()
            }
            if ((this.pass == null) || (this.login == null)){
                write(settings.pass,pass.text.toString())
                write(settings.login,login.text.toString())
            }
            http.login=login.text.toString()
            http.pass=pass.text.toString()
            http.TCP().execute()
            startActivity(intent)
        }
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    startActivity(intent)
                    Toast.makeText(applicationContext,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login")
            .setNegativeButtonText("Use account password")
            .build()

        val biometricLoginButton =
            findViewById(R.id.imageView) as ImageView
        biometricLoginButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
            Log.e("bio",promptInfo.isConfirmationRequired.toString())
        }

    }

    fun read(name: String): String {
        var fileInputStream: FileInputStream? = null
        fileInputStream = openFileInput("l.txt")
        var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
        var bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
        var stringBuilder: StringBuilder = StringBuilder()
        var text: String? = null
        while ({ text = bufferedReader.readLine(); text }() != null) {
            stringBuilder.append(text)
        }
        return stringBuilder.toString()
    }

    fun write(name: String,text: String){
        val filel:String = name
        val datal:String = text
        val filelOutputStream: FileOutputStream
        try {
            filelOutputStream = openFileOutput(filel, Context.MODE_PRIVATE)
            filelOutputStream.write(datal.toByteArray())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }


}