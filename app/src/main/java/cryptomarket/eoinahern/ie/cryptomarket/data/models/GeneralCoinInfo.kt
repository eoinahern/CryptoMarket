package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.TWITTER_BASE_URL
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.notAvailableStr

@JsonClass(generateAdapter = true)
data class GeneralCoinInfo(
		@Json(name = "Id")
		val id: String,
		@Json(name = "Description")
		val description: String,
		@Json(name = "Features")
		val features: String,
		@Json(name = "Twitter")
		val twitter: String,
		@Json(name = "WebsiteUrl")
		val websiteUrl: String)


fun GeneralCoinInfo.getTwitterFullLink(): String {

	return if (!twitter.isNullOrEmpty())
		TWITTER_BASE_URL.plus(twitter.substring(1)) else notAvailableStr

}
