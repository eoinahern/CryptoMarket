package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CoinMarketTickerData(

@Json(name= "data")
val data : Map<String, CoinMarketCrypto>



)



