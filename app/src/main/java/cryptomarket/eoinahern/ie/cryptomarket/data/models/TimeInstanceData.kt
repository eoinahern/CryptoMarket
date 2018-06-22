package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimeInstanceData(

		val time: Long,
		val close: Float,
		val high: Float,
		val low: Float,
		val open: Float,
		val volumefrom: Float,
		val volumeto: Float)