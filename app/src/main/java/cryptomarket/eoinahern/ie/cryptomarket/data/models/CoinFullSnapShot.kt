package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CoinFullSnapShot(
		val Response: String,
		val Message: String,
		val Data: SnapShotData
)