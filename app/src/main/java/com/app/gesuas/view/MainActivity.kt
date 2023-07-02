package com.app.gesuas.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.gesuas.R
import com.app.gesuas.utils.CPFUtil
import com.app.gesuas.utils.Mask

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initConfig()
    }

    private fun initConfig() {

        //initialize
        val etName = findViewById<EditText>(R.id.etName)
        val etCpf = findViewById<EditText>(R.id.etCpf)
        val etBirthDate = findViewById<EditText>(R.id.etBirthDate)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val btn = findViewById<Button>(R.id.btnRegister)

        val name = etName.text

        //Mask
        val cpf = etCpf.addTextChangedListener(Mask.mask("###.###.###-##", etCpf))
        val birthDate = etBirthDate.addTextChangedListener(Mask.mask("##/##/####", etBirthDate)).toString()
        val phone = etPhone.addTextChangedListener(Mask.mask("(##)#####-####", etPhone)).toString()

        btn.setOnClickListener {
        }
    }

    private fun nextActivity() {
        val intent = Intent(this, ServiceActivity::class.java)
        startActivity(intent)
    }

    private fun invalidMessage(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
