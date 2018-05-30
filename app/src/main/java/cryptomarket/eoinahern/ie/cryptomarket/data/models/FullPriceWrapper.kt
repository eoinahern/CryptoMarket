package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.Json

class FullPriceWrapper(
		@Json( name = "DISPLAY")
		val DISPLAY : Map<String, FullPriceWrapperInternalDisplay>
)