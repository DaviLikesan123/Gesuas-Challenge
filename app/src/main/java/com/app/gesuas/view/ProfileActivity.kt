package com.app.gesuas.view

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.app.gesuas.R
import com.app.gesuas.utils.AppBarTemplate
import com.app.gesuas.utils.CustomDialog
import com.app.gesuas.utils.SHAREDPREF_AGE
import com.app.gesuas.utils.SHAREDPREF_BIRTHDATE
import com.app.gesuas.utils.SHAREDPREF_CPF
import com.app.gesuas.utils.SHAREDPREF_FILENAME
import com.app.gesuas.utils.SHAREDPREF_NAME
import com.app.gesuas.utils.formatBrithDate
import com.app.gesuas.utils.formatCPF
import com.app.gesuas.utils.nextActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    lateinit var appBarTemplate: AppBarTemplate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        appBarTemplate = AppBarTemplate(this)

        initConfig()

    }

    private fun initConfig() {

        getSharedPref()

        val btnForward = findViewById<Button>(R.id.btnContinue)
        val btnFollow = findViewById<Button>(R.id.btnFollow)
        val btnReason = findViewById<Button>(R.id.btnReason)
        val editTextReason = findViewById<EditText>(R.id.typeReason)
        val btnCancel = findViewById<TextView>(R.id.cancel)
        val btnSave = findViewById<TextView>(R.id.save)

        val scroll = findViewById<ScrollView>(R.id.scrollView)
        val typeText = findViewById<ConstraintLayout>(R.id.reasonBox)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        editTextReason.inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
        editTextReason.imeOptions = EditorInfo.IME_FLAG_NO_ENTER_ACTION
        editTextReason.isSingleLine = false
        editTextReason.maxLines = Int.MAX_VALUE
        editTextReason.isVerticalScrollBarEnabled = true

        btnFollow.setOnClickListener {
            nextActivity(
                this,
                FollowActivity::class.java
            )
        }

        btnReason.setOnClickListener {
            scroll.visibility = View.GONE
            typeText.visibility = View.VISIBLE
            bottomNavigationView.visibility = View.GONE
        }

       btnCancel.setOnClickListener {
           visibleItems(scroll, typeText, bottomNavigationView)
       }

        btnSave.setOnClickListener {
            if (editTextReason.text.isNotEmpty() || editTextReason.text.isNotBlank() ) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(editTextReason.windowToken, 0)
                visibleItems(scroll, typeText, bottomNavigationView)

            } else {
                Toast.makeText(this, "não deixei em branco", Toast.LENGTH_SHORT).show()
            }
        }


        val customDialog = CustomDialog(this)

        btnForward.setOnClickListener {
            customDialog.show("Deseja confirmar o encaminhamento ?",
                showConfirmButton = true,
                showCancelButton = true,
                onConfirmClicked = {

                    customDialog.show("Encaminhamento realizado com sucesso",
                        confirmButtonText = "Voltar para cadastro",
                        showConfirmButton = true,
                        showCancelButton = false,
                        onConfirmClicked = {
                            nextActivity(this, MainActivity::class.java)
                            customDialog.dismiss()
                        }
                    )

                },
                onCancelClicked = {}
            )
        }
    }

    private fun getSharedPref() {
        val sharedPreferences = getSharedPreferences(SHAREDPREF_FILENAME, Context.MODE_PRIVATE)
        val getName: String? = sharedPreferences.getString(SHAREDPREF_NAME, "")
        val getCpf: String? = sharedPreferences.getString(SHAREDPREF_CPF, "")
        val getBirthDate: String? = sharedPreferences.getString(SHAREDPREF_BIRTHDATE, "")
        val getAge: Int = sharedPreferences.getInt(SHAREDPREF_AGE, 0)

        val name = findViewById<TextView>(R.id.name)
        val cpfValue = findViewById<TextView>(R.id.cpfValue)
        val birthDate = findViewById<TextView>(R.id.birthDateValue)
        val age = findViewById<TextView>(R.id.ageValue)

        val cpfFormated = formatCPF(getCpf.toString())
        val dataFormated = formatBrithDate(getBirthDate.toString())

        name.text = getName
        cpfValue.text = cpfFormated
        birthDate.text = dataFormated
        age.text = "$getAge anos"
    }

    private fun checkReasonAnswer(editText: EditText) : Boolean{
        if (editText.text.isBlank()){
            return false
        }
        return true
    }

    private fun visibleItems(scroll: ScrollView, typeText : ConstraintLayout, bottomNavigationView: BottomNavigationView){
        scroll.visibility = View.VISIBLE
        typeText.visibility = View.GONE
        bottomNavigationView.visibility = View.VISIBLE
    }

}