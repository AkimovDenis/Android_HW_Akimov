package com.example.roomapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: android.widget.ProgressBar
    private lateinit var database: AppDatabase

    private val PREFS_NAME = "prefs"
    private val KEY_DB_CLEARED = "db_cleared"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        val clearButton: Button = findViewById(R.id.clearButton)

        recyclerView.layoutManager = LinearLayoutManager(this)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "my-database"
        ).build()

        clearButton.setOnClickListener {
            clearDatabase()
        }

        seedDatabaseIfEmpty()
    }

    private fun loadItems() {
        lifecycleScope.launch {
            val items = withContext(Dispatchers.IO) {
                database.itemDao().getAllItems()
            }

            if (items.isEmpty()) {
                recyclerView.adapter = ItemAdapter(emptyList())
            } else {
                recyclerView.adapter = ItemAdapter(items)
            }

            progressBar.visibility = android.view.View.GONE
            recyclerView.visibility = android.view.View.VISIBLE
        }
    }

    private fun clearDatabase() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                database.clearAllTables()
            }

            recyclerView.adapter = ItemAdapter(emptyList())
            Toast.makeText(this@MainActivity, "База очищена", Toast.LENGTH_SHORT).show()
        }
    }

    private fun seedDatabaseIfEmpty() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                if (database.itemDao().getAllItems().isEmpty()) {
                    database.itemDao().insertItem(ItemEntity(name = "Пример элемента 1"))
                    database.itemDao().insertItem(ItemEntity(name = "Пример элемента 2"))
                    database.itemDao().insertItem(ItemEntity(name = "Пример элемента 3"))
                    database.itemDao().insertItem(ItemEntity(name = "Пример элемента 4"))
                    database.itemDao().insertItem(ItemEntity(name = "Пример элемента 5"))
                    database.itemDao().insertItem(ItemEntity(name = "Пример элемента 6"))
                }
            }
            loadItems()
        }
    }

}
