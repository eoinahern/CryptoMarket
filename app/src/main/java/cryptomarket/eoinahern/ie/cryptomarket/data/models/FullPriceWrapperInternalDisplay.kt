package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FullPriceWrapperInternalDisplay(
		val item : Map<String, CurrencyFullPriceDataDisplay>)