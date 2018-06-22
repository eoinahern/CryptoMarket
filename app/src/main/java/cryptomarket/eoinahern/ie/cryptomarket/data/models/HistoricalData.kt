package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HistoricalData(
		val Response: String,
		val Type: Int,
		val Data: List<TimeInstanceData>)
