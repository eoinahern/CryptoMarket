package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.notAvailableStr


@JsonClass(generateAdapter = true)
data class IcoData(
		@Json(name = "BlogLink")
		var blogLink: String?,
		@Json(name = "WhitePaper")
		val whitePaper: String)

fun IcoData.getBlogLink() = blogLink ?: notAvailableStr
