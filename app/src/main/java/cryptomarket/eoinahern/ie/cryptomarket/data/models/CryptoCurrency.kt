package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.google.gson.annotations.SerializedName

data class CryptoCurrency(


		@SerializedName("Id")
		val Id: String,
		@SerializedName("Url")
		val Url: String,
		@SerializedName("ImageUrl")
		val ImageUrl: String,
		val Name: String,
		val Symbol: String,
		val CoinName: String,
		val FullName: String,
		val Algorithm: String,
		val ProofType: String,
		val FullyPremined: String,
		val TotalCoinSupply: String,
		val SortOrder: String,
		val Sponsored: Boolean
)