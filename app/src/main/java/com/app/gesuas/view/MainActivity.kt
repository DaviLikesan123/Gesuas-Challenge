package com.app.gesuas.view

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.gesuas.R
import com.app.gesuas.utils.AppBarTemplate
import com.app.gesuas.utils.Mask
import com.app.gesuas.utils.SHAREDPREF_AGE
import com.app.gesuas.utils.SHAREDPREF_BIRTHDATE
import com.app.gesuas.utils.SHAREDPREF_CPF
import com.app.gesuas.utils.SHAREDPREF_FILENAME
import com.app.gesuas.utils.SHAREDPREF_NAME
import com.app.gesuas.utils.SHAREDPREF_PHONE
import com.app.gesuas.utils.nextActivity

class MainActivity : AppCompatActivity() {

    lateinit var appBarTemplate: AppBarTemplate

    private lateinit var editTextname: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appBarTemplate = AppBarTemplate(this)
        appBarTemplate.hideBack()

        initConfig()
    }

    private fun initConfig() {
        val btn = findViewById<Button>(R.id.btnRegister)
        btn.setOnClickListener {
            if (!checkFields()) invalidMessage(R.string.invalid_fields)
            else nextActivity(this, ServiceActivity::class.java)
        }
    }


    private fun invalidMessage(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun checkFields(): Boolean {
        // Initialize
        val etName = findViewById<EditText>(R.id.etName)
        val etCpf = findViewById<EditText>(R.id.etCpf)
        val etBirthDate = findViewById<EditText>(R.id.etBirthDate)
        val etPhone = findViewById<EditText>(R.id.etPhone)

//        etCpf.addTextChangedListener(Mask.mask("###.###.###-##", etCpf))
//        etBirthDate.addTextChangedListener(Mask.mask("##/##/####", etBirthDate))
//        etPhone.addTextChangedListener(Mask.mask("()#####-####", etPhone))

        val name = etName.text.toString()
        val cpf = etCpf.text.toString()
        val birthDate = etBirthDate.text.toString()
        val phone = etPhone.text.toString()

        if (name.isEmpty()) {
            invalidMessage(R.string.invalid_name)
            return false
        }

        if (cpf.isEmpty() || cpf.length != 11) {
            invalidMessage(R.string.invalid_cpf)
            return false
        }

        if(birthDate.isBlank()) {
            return true
        } else if (birthDate.isNotBlank() && birthDate.length != 8) {
            return false
        }

        if (phone.isBlank() || phone.length != 11 || phone.contains(" ")) {
            invalidMessage(R.string.invalid_phone)
            return false
        }

        val year = birthDate.substring(birthDate.length - 4)
        val age = 2023 - year.toInt()

        val sharedPreferences = getSharedPreferences(SHAREDPREF_FILENAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString(SHAREDPREF_NAME, name)
        editor.putString(SHAREDPREF_CPF, cpf)
        editor.putString(SHAREDPREF_BIRTHDATE, birthDate)
        editor.putString(SHAREDPREF_PHONE, phone)
        editor.putInt(SHAREDPREF_AGE, age)
        editor.apply()

        return true
    }
}
