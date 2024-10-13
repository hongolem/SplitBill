package com.example.splitbill

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var costText: EditText
    private lateinit var personText: TextView
    private lateinit var addButton: Button
    private lateinit var removeButton: Button
    private lateinit var calculateButton: Button
    private lateinit var tipButtons: List<Button>
    private lateinit var customTipInput: EditText
    private var numberOfPeople: Int = 1
    private var selectedTipPercentage: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        costText = findViewById(R.id.Price)
        personText = findViewById(R.id.People_number)
        addButton = findViewById(R.id.People_add)
        removeButton = findViewById(R.id.People_remove)
        calculateButton = findViewById(R.id.Calculate)
        customTipInput = findViewById(R.id.Tip_input)

        // Initialize tip buttons
        tipButtons = listOf(
            findViewById(R.id.Tip_0),
            findViewById(R.id.Tip_5),
            findViewById(R.id.Tip_10),
            findViewById(R.id.Tip_custom)
        )

        // Set up listeners for buttons
        addButton.setOnClickListener {
            numberOfPeople++
            updatePersonText()
        }

        removeButton.setOnClickListener {
            if (numberOfPeople > 1) {
                numberOfPeople--
                updatePersonText()
            }
        }

        calculateButton.setOnClickListener {
            calculatePerPerson()
        }

        tipButtons.forEach { button ->
            button.setOnClickListener {
                handleTipButtonClick(button)
            }
        }
    }

    private fun updatePersonText() {
        personText.text = numberOfPeople.toString()
    }

    private fun handleTipButtonClick(button: Button) {
        if (button.id == R.id.Tip_custom) {
            customTipInput.visibility = View.VISIBLE
            selectedTipPercentage = customTipInput.text.toString().toDoubleOrNull() ?: 0.0
        } else {
            customTipInput.visibility = View.GONE
            selectedTipPercentage = when (button.id) {
                R.id.Tip_0 -> 0.0
                R.id.Tip_5 -> 5.0
                R.id.Tip_10 -> 10.0
                else -> 0.0
            }
        }
    }

    private fun calculatePerPerson() {
        val totalCost = costText.text.toString().toDoubleOrNull() ?: 0.0
        if (customTipInput.visibility == View.VISIBLE) {
            selectedTipPercentage = customTipInput.text.toString().toDoubleOrNull() ?: 0.0
        }
        val tipAmount = totalCost * (selectedTipPercentage / 100)
        val totalAmount = totalCost + tipAmount
        val amountPerPerson = totalAmount / numberOfPeople

        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("amountPerPerson", amountPerPerson)
        }
        startActivity(intent)
    }
}