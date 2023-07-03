package com.app.gesuas.utils

import android.content.Context
import android.content.Intent

fun nextActivity(context: Context, chosenClass: Class<*>) {
    val intent = Intent(context, chosenClass)
    context.startActivity(intent)
}