package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CryptoCurrency(
		@Json(name = "Id")
		val Id: String,
		@Json(name = "Url")
		val Url: String,
		@Json(name = "ImageUrl")
		val ImageUrl: String = "not found",
		@Json(name = "Name")
		val Name: String,
		@Json(name = "Symbol")
		val Symbol: String,
		@Json(name = "CoinName")
		val CoinName: String,
		@Json(name = "FullName")
		val FullName: String)