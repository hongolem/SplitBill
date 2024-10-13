package com.example.splitbill

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultTextView: TextView = findViewById(R.id.result)
        val amountPerPerson = intent.getDoubleExtra("amountPerPerson", 0.0)
        resultTextView.text = String.format("%.2f", amountPerPerson)

        val backButton: Button = findViewById(R.id.back)
        backButton.setOnClickListener {
            finish()
        }
    }
}