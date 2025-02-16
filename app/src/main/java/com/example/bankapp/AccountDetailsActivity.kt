package com.example.bankapp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class AccountDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_details)

        // Set Status Bar Color
        window.statusBarColor = Color.parseColor("#616AE6")

        // Initialize Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar3)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Account Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Initialize Pie Chart
        val pieChart = findViewById<PieChart>(R.id.pieChart)
        setupPieChart(pieChart)
    }

    private fun setupPieChart(pieChart: PieChart) {
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(70f, "Saved Money"))
        entries.add(PieEntry(30f, "Spent Money"))

        val dataSet = PieDataSet(entries, "Financial Overview")
        dataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val data = PieData(dataSet)
        pieChart.data = data

        pieChart.description.isEnabled = false // Remove description label
        pieChart.centerText = "Balance"
        pieChart.setDrawEntryLabels(true)
        pieChart.animateY(1000)
    }
}


//package com.example.bankapp
//
//import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.Toolbar
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.core.view.setPadding
//
//class AccountDetailsActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_account_details)
//
//        //  Set Status Bar Color to Transparent
//        window.statusBarColor = android.graphics.Color.parseColor("#616AE6")
//        // Handling system insets
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        // Initialize Toolbar
//        val toolbar = findViewById<Toolbar>(R.id.toolbar3)
//        setSupportActionBar(toolbar)
//
//        // Set a custom title
//        supportActionBar?.title = "Account Details"
//
//        // Enable back navigation
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)
//
//        // Handle back button click
//        toolbar.setNavigationOnClickListener {
//            onBackPressedDispatcher.onBackPressed()
//        }
//    }
//}
//
