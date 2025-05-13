package com.example.hwlesson18

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.hwlesson18.R
import com.example.hwlesson18.model.City
import com.example.hwlesson18.model.Weather
import com.example.hwlesson18.ui.DetailsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val weather = Weather(City("Алматы", 43.238949, 76.889709))
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(weather))
                .commit()
        }
    }
}
