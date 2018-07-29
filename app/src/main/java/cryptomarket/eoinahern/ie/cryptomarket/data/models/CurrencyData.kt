package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyData(
		@Json(name = "Response")
		val response: String,
		@Json(name = "Message")
		val message: String,
		@Json(name = "BaseImageUrl")
		val baseImageUrl: String,
		@Json(name = "Data")
		val cryptoWrapper: Map<String, CryptoCurrency>
)
