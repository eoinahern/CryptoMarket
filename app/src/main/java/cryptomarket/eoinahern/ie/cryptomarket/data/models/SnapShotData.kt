package cryptomarket.eoinahern.ie.cryptomarket.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SnapShotData(
		@Json(name = "General")
		val General: GeneralCoinInfo,
		@Json(name = "ICO")
		val ico: IcoData)
