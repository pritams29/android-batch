package com.example.agecal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buGetAge.setOnClickListener {

            val userDOB = etDOB.text.toString().trim()

            val year:Int = Calendar.getInstance().get(Calendar.YEAR)



            if (userDOB == ""){
                Toast.makeText(this,"Please enter year", Toast.LENGTH_SHORT).show()

            }
            else if (userDOB > year.toString()){
                Toast.makeText(this, "Entered year should be less then current year",Toast.LENGTH_SHORT).show()
            }
            else{

                val myAge = year - userDOB.toInt()

                tvShowAge.text = "Age is " + myAge + "Years"

            }

        }

    }
}