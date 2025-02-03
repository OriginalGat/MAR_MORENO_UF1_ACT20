package com.example.actividad20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textoDescarga)
        button = findViewById(R.id.buttonLoad)

        button.setOnClickListener {
            runDownload()
        }


    }

    private fun runDownload() {
        textView.text = getString(R.string.string_descargando)
        button.isEnabled = false

        GlobalScope.launch(Dispatchers.IO) {
            val downloadTime = (2000..5000).random().toLong()
            delay(downloadTime)

            withContext(Dispatchers.Main) {
                textView.text = getString(R.string.descargado_correctamente)
                button.isEnabled = true
            }
        }
    }

}