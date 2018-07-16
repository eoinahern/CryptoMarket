package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.github.mikephil.charting.data.LineDataSet


data class HistoricalData(
		val Response: String,
		val Type: Int,
		val Data: List<TimeInstanceData>,
		val LineData : LineDataSet)

