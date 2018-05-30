package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.Json

data class CurrencyFullPriceDataDisplay(
		@Json(name = "FROMSYMBOL")
		val FROMSYMBOL: String,
		@Json(name = "TOSYMBOL")
		val TOSYMBOL: String,
		@Json(name = "MARKET")
		val MARKET: String,
		@Json(name = "PRICE")
		val PRICE: String,
		@Json(name = "LASTUPDATE")
		val LASTUPDATE: String)