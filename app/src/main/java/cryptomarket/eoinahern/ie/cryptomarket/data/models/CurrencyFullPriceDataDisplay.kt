package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class CurrencyFullPriceDataDisplay(
		@Json(name = "TOSYMBOL")
		val TOSYMBOL: String,
		@Json(name = "PRICE")
		val PRICE : String,
		@Json(name = "CHANGEPCT24HOUR")
		val CHANGEPCT24HOUR : String )


fun  CurrencyFullPriceDataDisplay.isMinus() : Boolean = (CHANGEPCT24HOUR[0]  == '-')
