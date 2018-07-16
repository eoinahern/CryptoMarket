package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.JsonClass
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.ICON_LOCATION
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.PNG_SUFFIX

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


fun CoinMarketCrypto.getIconUrl() = ICON_LOCATION.plus(id.toString()).plus(PNG_SUFFIX)