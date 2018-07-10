package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SnapShotData(
		@SerializedName("General")
		val General: GeneralCoinInfo)