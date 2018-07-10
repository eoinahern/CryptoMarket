package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeneralCoinInfo(
		val Id: String,
		val DocumentType: String,
		val H1Text: String,
		val ImageUrl: String,
		val Description: String,
		val Features: String,
		val Technology: String)