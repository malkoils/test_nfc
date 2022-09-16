package com.example.test_nfc

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.net.Socket
class http {
    companion object{
        var answer=""
        var pass=""
        var login=""
    }

    fun check_auth(){
        TCP().execute()
    }

    @SuppressLint("StaticFieldLeak")
    class TCP : AsyncTask<String, String, String>() {
        @SuppressLint("WrongThread")
        override fun doInBackground(vararg data: String): String {



            val payload = "test payload"

            val okHttpClient = OkHttpClient()
            val requestBody = payload.toRequestBody()
            val request = Request.Builder()
                .method("POST", requestBody)
                .url(settings.server)
                .build()
            okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("HTTP POST ERROR",e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.e("HTTP POST RESPONSE",response.toString())
                    answer=response.toString()
                }
            })
            return "good"
        }
    }
}