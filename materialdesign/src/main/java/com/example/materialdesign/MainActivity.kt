package com.example.materialdesign

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                txt.text = "Settings"
                return true
            }
            R.id.action_cut -> {
                txt.text = "Cut"
                return true
            }
            R.id.action_copy -> {
                txt.text = "Copy"
                return true
            }
            R.id.action_paste -> {
                txt.text = "Paste"
                return true
            }
            R.id.action_search -> {
                txt.text = "Search"
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
