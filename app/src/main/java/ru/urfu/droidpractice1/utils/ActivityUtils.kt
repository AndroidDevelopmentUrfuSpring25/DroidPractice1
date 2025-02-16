package ru.urfu.droidpractice1.utils

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity

fun <T: ComponentActivity> Activity.startOtherActivity(clazz: Class<T>) {
    val intent = Intent(this, clazz)
    startActivity(intent)
}