package com.example.actividad20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textoDescarga)
        button = findViewById(R.id.buttonLoad)

        button.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                awaitDownload()
            }
        }
    }

    private suspend fun awaitDownload() {
        textView.text = getString(R.string.string_descargando)
        button.isEnabled = false

        simulateDownload()

        textView.text = getString(R.string.descargado_correctamente)
        button.isEnabled = true
    }

    private suspend fun simulateDownload() {
        val downloadTime = Random.nextLong(2000, 5000)
        withContext(Dispatchers.IO) {
            kotlinx.coroutines.delay(downloadTime)
        }
    }
}