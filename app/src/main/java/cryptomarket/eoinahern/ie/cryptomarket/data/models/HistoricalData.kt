package cryptomarket.eoinahern.ie.cryptomarket.data.models

data class HistoricalData(
		val Response: String,
		val Type: Int,
		val Data: List<TimeInstanceData>)
