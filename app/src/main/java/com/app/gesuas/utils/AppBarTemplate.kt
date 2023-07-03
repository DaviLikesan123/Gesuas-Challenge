package com.app.gesuas.utils


import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import com.app.gesuas.R

class AppBarTemplate(private val activity: Activity) {

    private val arrow: ImageView = activity.findViewById(R.id.backArrow)

    init {

        arrow.setOnClickListener {
            activity.onBackPressed()
        }
    }

    fun hideBack() {
        arrow.visibility = View.GONE
    }
}


