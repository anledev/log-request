package com.anledev.logrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.anledev.logrequest.networks.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApiService.invoke().getDemo().enqueue(object : Callback<Any> {
            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.e("~~", "ERROR")
            }

            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.i("~~", "SUCCESS")
            }
        })
    }
}
