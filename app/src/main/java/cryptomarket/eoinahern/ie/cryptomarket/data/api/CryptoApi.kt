package cryptomarket.eoinahern.ie.cryptomarket.data.api

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyData
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET


interface CryptoApi {

	@GET("api/data/coinlist")
	fun getList() : Observable<CurrencyData>

}