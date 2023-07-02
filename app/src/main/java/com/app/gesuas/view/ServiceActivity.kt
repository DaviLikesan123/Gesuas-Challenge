package com.app.gesuas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView
import com.app.gesuas.R

class ServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        initConfig()

    }

    private fun initConfig() {
        val checkbox1 = findViewById<CheckBox>(R.id.checkboxFollow)
        val checkbox2 = findViewById<CheckBox>(R.id.checkboxForwarding)
        val checkbox3 = findViewById<CheckBox>(R.id.checkboxReception)
        val checkbox4 = findViewById<CheckBox>(R.id.checkboxScfv)
        val checkbox5 = findViewById<CheckBox>(R.id.checkboxSocialApproach)

        val checkboxes = listOf(checkbox1, checkbox2, checkbox3, checkbox4, checkbox5)

        for (checkbox in checkboxes) {
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkboxes.forEach { otherCheckbox ->
                        if (otherCheckbox != checkbox) {
                            otherCheckbox.isChecked = false
                        }
                    }
                }
            }
        }
    }
}
