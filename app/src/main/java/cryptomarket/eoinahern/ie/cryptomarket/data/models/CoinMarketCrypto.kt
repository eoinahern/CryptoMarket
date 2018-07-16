package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinMarketCrypto(
		val id: Int,
		val name: String,
		val symbol: String,
		val website_slug: String?,
		val rank: Int?,
		val circulating_supply: Long?,
		val total_supply: Long?,
		var max_supply: Long?,
		val quotes: Map<String, CoinMarketQuotes>)