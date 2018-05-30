package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FullPriceWrapper(
		@Json( name = "DISPLAY")
		val DISPLAY : Map<String, FullPriceWrapperInternalDisplay>
)