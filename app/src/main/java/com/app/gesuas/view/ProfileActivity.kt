package com.app.gesuas.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.Window
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.app.gesuas.R
import com.app.gesuas.utils.SHAREDPREF_AGE
import com.app.gesuas.utils.SHAREDPREF_BIRTHDATE
import com.app.gesuas.utils.SHAREDPREF_CPF
import com.app.gesuas.utils.SHAREDPREF_FILENAME
import com.app.gesuas.utils.SHAREDPREF_NAME
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initConfig()

    }

    private fun initConfig() {

        getSharedPref()

        val btnForward = findViewById<Button>(R.id.btnContinue)
        val btnFollow = findViewById<Button>(R.id.btnFollow)
        val btnReason = findViewById<Button>(R.id.btnReason)
        val editText = findViewById<EditText>(R.id.typeReason)
        val btnCancel = findViewById<TextView>(R.id.cancel)
        val btnSave = findViewById<TextView>(R.id.save)

        val scroll = findViewById<ScrollView>(R.id.scrollView)
        val typeText = findViewById<ConstraintLayout>(R.id.reasonBox)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        editText.inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
        editText.imeOptions = EditorInfo.IME_FLAG_NO_ENTER_ACTION
        editText.isSingleLine = false
        editText.maxLines = Int.MAX_VALUE
        editText.isVerticalScrollBarEnabled = true

        btnFollow.setOnClickListener { com.app.gesuas.utils.nextActivity(this, FollowActivity::class.java) }
        btnForward.setOnClickListener { showCustomDialog(message = "Deseja confirmar o encaminhamento ?")
        }

        btnReason.setOnClickListener {
            scroll.visibility = View.GONE
            typeText.visibility = View.VISIBLE
            bottomNavigationView.visibility = View.GONE
        }

        btnCancel.setOnClickListener {
            scroll.visibility = View.VISIBLE
            typeText.visibility = View.GONE
            bottomNavigationView.visibility = View.VISIBLE
        }

        btnSave.setOnClickListener {
            scroll.visibility = View.VISIBLE
            typeText.visibility = View.GONE
            bottomNavigationView.visibility = View.VISIBLE
        }

    }

    private fun showCustomDialog(message: String?){
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
        dialog.show()
    }

    private fun getSharedPref(){
        val sharedPreferences = getSharedPreferences(SHAREDPREF_FILENAME, Context.MODE_PRIVATE)
        val getName: String? = sharedPreferences.getString(SHAREDPREF_NAME, "")
        val getCpf: String? = sharedPreferences.getString(SHAREDPREF_CPF, "")
        val getBirthDate: String? = sharedPreferences.getString(SHAREDPREF_BIRTHDATE, "")
        val getAge: Int = sharedPreferences.getInt(SHAREDPREF_AGE, 0)

        val name = findViewById<TextView>(R.id.name)
        val cpfValue = findViewById<TextView>(R.id.cpfValue)
        val birthDate = findViewById<TextView>(R.id.birthDateValue)
        val age = findViewById<TextView>(R.id.ageValue)

        name.text = getName
        cpfValue.text = getCpf
        birthDate.text = getBirthDate
        age.text = "$getAge anos"
    }

}