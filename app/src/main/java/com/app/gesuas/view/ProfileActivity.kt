package com.app.gesuas.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.app.gesuas.R

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnForward = findViewById<Button>(R.id.btnContinue)
        btnForward.setOnClickListener {
            showCustomDialog(message = R.string.send.toString())
        }

    }

    private fun showCustomDialog(message: String){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvMessage : TextView = dialog.findViewById(R.id.tvMessage)
        val btnCancel : Button = dialog.findViewById(R.id.btnCancel)
        val btnConfirm : Button = dialog.findViewById(R.id.btnConfirm)

        tvMessage.text = message

        btnConfirm.setOnClickListener {

        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.dismiss()
    }
}