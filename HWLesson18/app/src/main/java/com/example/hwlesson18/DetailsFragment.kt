package com.example.hwlesson18.ui

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.example.hwlesson18.databinding.FragmentDetailsBinding
import com.example.hwlesson18.model.Weather
import com.example.hwlesson18.model.WeatherDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var weatherBundle: Weather

    companion object {
        private const val BUNDLE_NAME = "weather"

        fun newInstance(weather: Weather): DetailsFragment {
            val fragment = DetailsFragment()
            val bundle = Bundle().apply {
                putParcelable(BUNDLE_NAME, weather)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_NAME) ?: Weather()
        loadWeather()
    }

    private fun loadWeather() {
        Thread {
            val urlText =
                "https://api.weather.yandex.ru/v2/forecast?lat=${weatherBundle.city.lat}&lon=${weatherBundle.city.lon}"
            val url = URL(urlText)
            val connection = url.openConnection() as HttpsURLConnection
            connection.addRequestProperty("X-Yandex-API-Key", "demo_yandex_weather_api_key_ca6d09349ba0")

            try {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val result = getLines(reader)

                val weatherDTO = Gson().fromJson(result, WeatherDTO::class.java)

                requireActivity().runOnUiThread {
                    displayWeather(weatherDTO)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection.disconnect()
            }
        }.start()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    private fun displayWeather(weatherDTO: WeatherDTO) {
        binding.cityName.text = weatherBundle.city.city
        binding.temperatureValue.text = "${weatherDTO.fact?.temp ?: "--"}°C"
        binding.conditionValue.text = weatherDTO.fact?.condition ?: "нет данных"
        binding.feelsLikeValue.text = "${weatherDTO.fact?.feels_like ?: "--"}°C"
    }
}
