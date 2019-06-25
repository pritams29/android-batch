package com.example.constraintlayout3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cal=findViewById<CalendarView>(R.id.cal)
        cal.setOnDateChangeListener {_,year, month,dayOfMonth->
            txtAg.text=ageobj.calAge(year,month,dayOfMonth).toString()
        }
    }
}
