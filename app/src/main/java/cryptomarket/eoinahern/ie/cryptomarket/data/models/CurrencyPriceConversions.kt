package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyPriceConversions(

		@Json(name = "BTC")
		val btc: Float,
		@Json(name = "EUR")
		val eur: Float,
		@Json(name = "USD")
		val usd: Float,
		@Json(name = "SGD")
		val sgd: Float,
		@Json(name = "PLN")
		val pln: Float,
		@Json(name = "AUD")
		val aud: Float,
		@Json(name = "RUB")
		val rub: Float,
		@Json(name = "CAD")
		val cad: Float,
		@Json(name = "BRL")
		val brl: Float,
		@Json(name = "HKD")
		val hkd: Float,
		@Json(name = "INR")
		val inr: Float,
		@Json(name = "KRW")
		val krw: Float,
		@Json(name = "CHF")
		val chf: Float,
		@Json(name = "ZAR")
		val zar: Float,
		@Json(name = "NZD")
		val nzd: Float,
		@Json(name = "GOLD")
		val gold: Float,
		@Json(name = "ETH")
		val eth: Float,
		@Json(name = "GBP")
		val gbp: Float
)

