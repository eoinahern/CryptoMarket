package cryptomarket.eoinahern.ie.cryptomarket.data.api

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import retrofit2.http.GET
import retrofit2.http.Header

interface CryptoNewsApi {

	@GET("/news")
	fun getNews(@Header("x-api-key") apiKey: String) : List<CryptoNewsItem>
}