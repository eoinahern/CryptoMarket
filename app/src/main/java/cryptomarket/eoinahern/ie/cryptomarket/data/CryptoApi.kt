package cryptomarket.eoinahern.ie.cryptomarket.data

import retrofit2.http.GET


interface CryptoApi {


	@GET("api/data/coinlist")
	fun getList()

}