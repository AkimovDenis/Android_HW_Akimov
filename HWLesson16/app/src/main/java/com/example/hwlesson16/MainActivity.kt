package com.example.hwlesson16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hwlesson16.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cities = listOf(
            Weather("Almaty", "+24°C, Clear"),
            Weather("Astana", "+18°C, Cloudy"),
            Weather("Shymkent", "+28°C, Sunny"),
            Weather("Aktau", "+22°C, Windy"),
            Weather("Karaganda", "+16°C, Rain")
        )

        weatherAdapter = WeatherAdapter(cities)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = weatherAdapter
    }
}
