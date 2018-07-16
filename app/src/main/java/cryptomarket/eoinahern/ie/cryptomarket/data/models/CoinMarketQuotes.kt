package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CoinMarketQuotes(
		val price: Float?,
		val volume_24h: Double?,
		val market_cap: Long?,
		val percent_change_1h: Float?,
		val percent_change_24h: Float?,
		val percent_change_7d: Float?)



