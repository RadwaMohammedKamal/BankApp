package com.example.bankapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.View
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
        findViewById<View>(android.R.id.content).setBackgroundColor(resources.getColor(R.color.white, theme))
        // Set Status Bar Color
        window.statusBarColor = Color.parseColor("#616AE6")

        // Initialize Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar3)
        setSupportActionBar(toolbar)

        // ✅ تغيير عنوان الـ Toolbar وجعله Bold
        val title = SpannableString("Account Details").apply {
            setSpan(StyleSpan(Typeface.BOLD), 0, length, 0) // جعل العنوان Bold
            setSpan(RelativeSizeSpan(1.2f), 0, title.length, 0) // تكبير الحجم 1.4x
            setSpan(ForegroundColorSpan(Color.WHITE), 0, length, 0) // جعل لون العنوان أبيض
        }

        supportActionBar?.apply {
            this.title = title
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // ✅ تغيير لون نص الـ AppBar
        toolbar.setTitleTextColor(Color.WHITE)

        // ✅ تغيير لون سهم الرجوع
        toolbar.navigationIcon?.setTint(Color.WHITE)

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


