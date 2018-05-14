package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.google.gson.annotations.SerializedName


data class CurrencyData  constructor(

		@SerializedName("Response")
		val response : String,
		@SerializedName("Message")
		val message : String,
		@SerializedName("BaseImageUrl")
		val baseImageUrl : String,
		@SerializedName("Data")
		val cryptoWrapper : Map<String, CryptoCurrency>


)
