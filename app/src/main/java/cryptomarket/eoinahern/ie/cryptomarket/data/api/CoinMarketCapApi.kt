package cryptomarket.eoinahern.ie.cryptomarket.data.api

import retrofit2.http.GET
import retrofit2.http.Query


interface CoinMarketCapApi {

	@GET("ticker/")
	fun getTickerData(@Query("convert") currency: String)
}