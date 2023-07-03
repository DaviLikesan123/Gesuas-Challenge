package com.app.gesuas.view

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.app.gesuas.R
import com.app.gesuas.utils.nextActivity

class ServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        initConfig()

    }

    private fun initConfig() {
        checkboxCheck()

        val btn = findViewById<Button>(R.id.btnRegister)
        btn.setOnClickListener {
           nextActivity(this, ProfileActivity::class.java)
        }
    }

    private fun checkboxCheck(){
        val checkbox1 = findViewById<CheckBox>(R.id.checkboxFollow)
        val checkbox2 = findViewById<CheckBox>(R.id.checkboxForwarding)
        val checkbox3 = findViewById<CheckBox>(R.id.checkboxReception)
        val checkbox4 = findViewById<CheckBox>(R.id.checkboxScfv)
        val checkbox5 = findViewById<CheckBox>(R.id.checkboxSocialApproach)

        val checkboxes = listOf(checkbox1, checkbox2, checkbox3, checkbox4, checkbox5)

        for (i in checkboxes.indices) {
            checkboxes[i].setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    for (j in checkboxes.indices) {
                        if (j != i) {
                            checkboxes[j].isChecked = false
                        }
                    }
                }
            }
        }
    }
}
