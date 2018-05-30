package cryptomarket.eoinahern.ie.cryptomarket.data.api

import cryptomarket.eoinahern.ie.cryptomarket.data.models.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CryptoApi {

	@GET("all/coinlist")
	fun getList(): Observable<CurrencyData?>

	@GET("pricemulti")
	@Headers("Content-Type: application/json")
	fun getPriceData(@Query("fsyms") currencies: String, @Query("tsyms") prices: String): Observable<HashMap<String, CurrencyPriceConversions>>

	@GET("pricemultifull")
	fun getFullPriceData(@Query("fsyms") currencies : String, @Query("tsyms") conversions : String) : Observable<FullPriceWrapper?>
}