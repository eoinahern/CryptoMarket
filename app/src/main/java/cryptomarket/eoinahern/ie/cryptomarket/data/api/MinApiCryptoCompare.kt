package cryptomarket.eoinahern.ie.cryptomarket.data.api

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyPriceConversions
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface MinApiCryptoCompare {

	@GET("pricemulti")
	@Headers("Content-Type: application/json")
	fun getPriceData(@Query("fsyms") currencies: String, @Query("tsyms") prices: String): Observable<HashMap<String, CurrencyPriceConversions>>
}