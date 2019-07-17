package com.example.httpobervables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// This problem we need to solve
// btn.setOnClickListener {
//            Thread {
//                for (i in 0..100) {
//                    Thread.sleep(2000)
//                    txt.text = "$i"
//                }
//            }.start()
//        }

        btn.setOnClickListener {

            myobs()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext { num -> txt.text = "$num" }
                    .doOnError{ err -> Log.e("@codekul", err.toString()) }
                    .doOnComplete { Log.e("@cocdekul", "Processing completed") }
                    .subscribe()

        }
    }

    fun myobs() : Observable<Int> = Observable.create {
        oe : ObservableEmitter<Int> ->
        for (i in 0..100) {
            Thread.sleep(2000)
            oe.onNext(i)
        }
        oe.onComplete()
    }

}