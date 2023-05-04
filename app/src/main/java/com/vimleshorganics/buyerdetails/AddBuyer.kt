package com.vimleshorganics.buyerdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.vimleshorganics.buyerdetails.R

class AddBuyer : AppCompatActivity() {
    var ed_customername: TextView? = null
    var ed_mobilenumber: TextView? = null
    var ed_address: TextView? = null
    var ed_price: TextView? = null
    var calendarView: CalendarView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addbuyer)

        ed_customername = findViewById(R.id.ed_customername)
        ed_mobilenumber = findViewById(R.id.ed_mobilenumber)
        ed_address = findViewById(R.id.ed_address)
        ed_price = findViewById(R.id.ed_price)
        calendarView = findViewById(R.id.calendarView)

        findViewById<Button>(R.id.btAddbuyerdetails).setOnClickListener {
            storeCustomerDetails(
                ed_customername?.text,
                ed_mobilenumber?.text,
                ed_address?.text,
                ed_price?.text,
                calendarView?.date
            )
        }
    }

    private fun storeCustomerDetails(
        text: CharSequence?,
        text1: CharSequence?,
        text2: CharSequence?,
        text3: CharSequence?,
        date: Long?
    ) {

        var rootNode: FirebaseDatabase = FirebaseDatabase.getInstance()
        var reference: DatabaseReference = rootNode.getReference()
        reference.setValue(" This is my first record")
    }
}