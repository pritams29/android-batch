package com.example.constraintlayout3

import android.icu.util.Calendar

object ageobj{
    fun  calAge(year:Int,month:Int,day:Int):Int{

        val dob=Calendar.getInstance()
        val today=Calendar.getInstance()
        dob.set(year,month,day)
        val age=today.get(Calendar.YEAR)-dob.get(Calendar.YEAR)
        if (today.get(Calendar.YEAR)<dob.get(Calendar.YEAR)){

        }
        return age
    }
}

