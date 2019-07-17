package com.example.retrofit.activity

import android.R
import android.app.ProgressDialog
import android.os.Bundle
import android.telecom.Call
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.adapter.CustomAdapter
import com.example.retrofit.model.RetroPhoto
import com.example.retrofit.network.GetDataService
import com.example.retrofit.network.RetrofitClientInstance
import retrofit2.Response
import javax.security.auth.callback.Callback
import javax.xml.ws.Response
import CustomAdapter
import com.example.retrofit.model.RetroPhoto
import com.example.retrofit.network.GetDataService
import com.example.retrofit.network.RetrofitClientInstance


class MainActivity : AppCompatActivity() {
    private var adapter: CustomAdapter? = null
    private var recyclerView: RecyclerView? = null
    internal var progressDoalog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDoalog = ProgressDialog(this@MainActivity)
        progressDoalog.setMessage("Loading....")
        progressDoalog.show()

        /*Create handle for the RetrofitInstance interface*/
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.allPhotos
        call.enqueue(object : Callback<List<RetroPhoto>> {
            fun onResponse(call: Call<List<RetroPhoto>>, response: Response<List<RetroPhoto>>) {
                progressDoalog.dismiss()
                generateDataList(response.body())
            }

            fun onFailure(call: Call<List<RetroPhoto>>, t: Throwable) {
                progressDoalog.dismiss()
                Toast.makeText(this@MainActivity, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private fun generateDataList(photoList: List<RetroPhoto>?) {
        recyclerView = findViewById(R.id.customRecyclerView)
        adapter = CustomAdapter(this, photoList!!)
        val layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = adapter
    }
}
