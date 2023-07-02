package com.app.gesuas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.get
import com.app.gesuas.R

class FollowActivity : AppCompatActivity() {

    private val types = arrayOf(
        "CRAS",
        "CREAS",
        "Defensoria Pública",
        "Ministério Público",
        "Poder Judiciário")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow)

        startSpinner()

    }

    private fun startSpinner(){
        val spinner = findViewById<Spinner>(R.id.spinner)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                onBackPressed()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                spinner[1]
            }
        }

    }
}