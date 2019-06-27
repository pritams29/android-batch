package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val dataSet = ArrayList<MyData>()
        dataSet.add(
            MyData(
                R.drawable.ic_mic_black_24dp,
                "Android"
            )
        )
        dataSet.add(
            MyData(
                R.drawable.ic_mic_black_24dp,
                "Apple"
            )
        )
        dataSet.add(
            MyData(
                R.drawable.ic_mic_black_24dp,
                "Samsung"
            )
        )
        dataSet.add(
            MyData(
                R.drawable.ic_mic_black_24dp,
                "Windows"
            )
        )
        dataSet.add(
            MyData(
                R.drawable.ic_mic_black_24dp,
                "Rim"
            )
        )
        val myAdapter = MyAdapter(
            this,
            dataSet
        )

        val viewManager = LinearLayoutManager(this)
        val recVw = findViewById<RecyclerView>(R.id.recVw)
        recVw.apply {

            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = myAdapter
        }
    }
}
