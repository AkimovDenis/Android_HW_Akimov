package com.example.contentproviderapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Telephony
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contentproviderapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val smsList = mutableListOf<Sms>()
    private lateinit var adapter: SmsAdapter

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                loadSmsMessages()
            } else {
                Toast.makeText(this, "В доступе отказано.", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SmsAdapter(smsList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.requestPermissionButton.setOnClickListener {
            checkAndRequestSmsPermission()
        }

        checkAndRequestSmsPermission()
    }

    private fun checkAndRequestSmsPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_SMS
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadSmsMessages()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_SMS) -> {
                Toast.makeText(
                    this,
                    "Приложению требуется разрешение для чтения СМС",
                    Toast.LENGTH_LONG
                ).show()
                requestPermissionLauncher.launch(Manifest.permission.READ_SMS)
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_SMS)
            }
        }
    }

    private fun loadSmsMessages() {
        smsList.clear()

        val uri = Telephony.Sms.CONTENT_URI
        val projection = arrayOf(
            Telephony.Sms.ADDRESS,
            Telephony.Sms.BODY,
            Telephony.Sms.DATE
        )

        contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
            val addressIndex = cursor.getColumnIndex(Telephony.Sms.ADDRESS)
            val bodyIndex = cursor.getColumnIndex(Telephony.Sms.BODY)
            val dateIndex = cursor.getColumnIndex(Telephony.Sms.DATE)

            while (cursor.moveToNext()) {
                val sender = cursor.getString(addressIndex) ?: "Неизвестный"
                val message = cursor.getString(bodyIndex) ?: ""
                val date = cursor.getLong(dateIndex).let { timestamp ->
                    SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(timestamp))
                }
                smsList.add(Sms(sender, message, date))
            }
        }

        if (smsList.isEmpty()) {
            Toast.makeText(this, "Вообще нет СМС.", Toast.LENGTH_SHORT).show()

            smsList.add(Sms("Google", "Ваш код: 1234", "17/05/2025 15:30"))
            smsList.add(Sms("Банк", "Списано 1200 тг", "17/05/2025 15:32"))
        }

        adapter.notifyDataSetChanged()
    }
}
