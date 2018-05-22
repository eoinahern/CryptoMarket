package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.google.gson.annotations.SerializedName

data class CryptoCurrency(

		val Id: String,
		val Url: String,
		val ImageUrl: String,
		val Name: String,
		val Symbol: String,
		val CoinName: String,
		val FullName: String

)