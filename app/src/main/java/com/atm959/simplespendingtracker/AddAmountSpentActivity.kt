package com.atm959.simplespendingtracker

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class AddAmountSpentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_amount_spent)

        val currencySymbol = findViewById<TextView>(R.id.currency_symbol_label)
        currencySymbol?.text = Currency.getInstance(Locale.getDefault()).symbol

        val cancelButton = findViewById<Button>(R.id.cancel_button)
        cancelButton.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                if (p1!!.action == MotionEvent.ACTION_DOWN) finish()
                return true
            }
        })
    }
}