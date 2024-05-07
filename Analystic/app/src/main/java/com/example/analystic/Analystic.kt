package com.example.analystic

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import androidx.compose.runtime.livedata.observeAsState



@Composable
fun BarChartScreen(viewModel: ScoreViewModel) {
    val dailyScores = viewModel.dailyScores.observeAsState(initial = emptyList())

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            BarChart(context).apply {
                val entries = dailyScores.value.mapIndexed { index, score ->
                    BarEntry(index.toFloat(), score.totalScore.toFloat())
                }
                val barDataSet = BarDataSet(entries, "Daily Scores")
                barDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
                val barData = BarData(barDataSet)
                barData.barWidth = 0.9f

                data = barData
                description.isEnabled = false
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.valueFormatter = IndexAxisValueFormatter(dailyScores.value.map { it.day })
                setFitBars(true)
                animateY(1500)
            }
        }
    )
}

