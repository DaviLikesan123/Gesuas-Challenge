package com.app.gesuas.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.app.gesuas.R

class CustomDialog(private val context: Context) {
    private val dialog: Dialog = Dialog(context)

    init {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun show(
        message: String?,
        confirmButtonText: String = "Confirmar",
        cancelButtonText: String = "Cancelar",
        showConfirmButton: Boolean = true,
        showCancelButton: Boolean = true,
        onConfirmClicked: () -> Unit = {},
        onCancelClicked: () -> Unit = {}
    ) {
        val tvMessage: TextView = dialog.findViewById(R.id.tvMessage)
        val btnCancel: Button = dialog.findViewById(R.id.btnCancel)
        val btnConfirm: Button = dialog.findViewById(R.id.btnConfirm)

        tvMessage.text = message

        btnConfirm.text = confirmButtonText
        btnCancel.text = cancelButtonText

        if (!showConfirmButton) {
            btnConfirm.visibility = View.GONE
        }

        if (!showCancelButton) {
            btnCancel.visibility = View.GONE
        }

        btnConfirm.setOnClickListener {
            onConfirmClicked()

        }

        btnCancel.setOnClickListener {
            onCancelClicked()
            dialog.dismiss()
        }

        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }
}
