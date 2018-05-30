package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyFullPriceDataDisplay(
		@Json(name = "FROMSYMBOL")
		val FROMSYMBOL: String,
		@Json(name = "TOSYMBOL")
		val TOSYMBOL: String,
		@Json(name = "MARKET")
		val MARKET: String)