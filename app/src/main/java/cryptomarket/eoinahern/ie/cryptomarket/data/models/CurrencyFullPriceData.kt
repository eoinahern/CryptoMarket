package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.google.gson.annotations.SerializedName

data class CurrencyFullPriceData(

		@SerializedName("PRICE")
		val price : Float,
		@SerializedName("CHANGEPCT24HOUR")
		val changePct24Hour : Float
)