package edu.farmingdale.alrajab.individual_project3

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class Reportactivity : AppCompatActivity() {
    lateinit var barChart: BarChart

    // on below line we are creating
    // a variable for bar data
    lateinit var barData: BarData

    // on below line we are creating a
    // variable for bar data set
    lateinit var barDataSet: BarDataSet

    // on below line we are creating array list for bar data
    lateinit var barEntriesList: ArrayList<BarEntry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_reportactivity)
        barChart = findViewById(R.id.idBarChart)

        getBarChartData()

        // on below line we are initializing our bar data set
        barDataSet = BarDataSet(barEntriesList, "Bar Chart Data")

        // on below line we are initializing our bar data
        barData = BarData(barDataSet)

        // on below line we are setting data to our bar chart
        barChart.data = barData

        // on below line we are setting colors for our bar chart text
        barDataSet.valueTextColor = Color.BLACK

        // on below line we are setting color for our bar data set
        barDataSet.setColor(resources.getColor(R.color.purple_200))

        // on below line we are setting text size
        barDataSet.valueTextSize = 16f
        val xAxisLabels = listOf("As", "Bs", "Cs", "Ds", "Fs")
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)
        barChart.xAxis.setCenterAxisLabels(true)

        // on below line we are enabling description as false
        barChart.description.isEnabled = false
    }


    private fun getBarChartData() {
        barEntriesList = ArrayList()

//     on below line we are adding data
//     to our bar entries list
        barEntriesList.add(BarEntry(1f, intent.getFloatExtra("agrade", 0F)))
        barEntriesList.add(BarEntry(2f, intent.getFloatExtra("bgrade", 0F)))
        barEntriesList.add(BarEntry(3f, intent.getFloatExtra("cgrade", 0F)))
        barEntriesList.add(BarEntry(4f, intent.getFloatExtra("dgrade", 0F)))
        barEntriesList.add(BarEntry(5f, intent.getFloatExtra("fgrade", 0F)))
    }
}



