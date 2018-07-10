package cryptomarket.eoinahern.ie.cryptomarket.data.api

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CoinFullSnapShot
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApiOld {

	@GET("coinsnapshotfullbyid/")
	public fun getCoinSnapShotById(@Query("id") id: String): Observable<CoinFullSnapShot>
}