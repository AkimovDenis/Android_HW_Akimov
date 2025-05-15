package com.example.lessons11


import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lessons11.data.fake.model.Weather
import com.example.lessons11.databinding.ActivityMainBinding
import com.example.lessons11.databinding.ActivityMainBrowserBinding
import java.util.Objects

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}