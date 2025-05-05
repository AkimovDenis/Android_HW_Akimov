package com.example.hwlesson24

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView = TextView(this)
        textView.text = "API_URL: ${BuildConfig.API_URL}\nApp Name: ${getString(R.string.app_name)}"
        setContentView(textView)
    }
}
