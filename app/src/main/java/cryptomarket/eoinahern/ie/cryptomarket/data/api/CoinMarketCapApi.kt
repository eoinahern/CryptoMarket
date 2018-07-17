package cryptomarket.eoinahern.ie.cryptomarket.data.api

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CoinMarketTickerData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface CoinMarketCapApi {

	@GET("ticker/")
	fun getTickerData(@Query("start") start: String,
					  @Query("limit") limit: String,
					  @Query("convert") currency: String = "USD"): Observable<CoinMarketTickerData>
}