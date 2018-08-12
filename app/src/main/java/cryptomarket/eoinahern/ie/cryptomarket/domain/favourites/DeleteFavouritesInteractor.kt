package cryptomarket.eoinahern.ie.cryptomarket.domain.favourites

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.cryptocompare.CryptoCompareCache
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject


@PerScreen
class DeleteFavouritesInteractor @Inject constructor(private val cryptoCompareCache: CryptoCompareCache) : BaseInteractor<Unit>() {

	private lateinit var symbol: String

	fun init(symbolin: String): DeleteFavouritesInteractor {
		this.symbol = symbolin
		return this
	}

	override fun buildObservable(): Observable<Unit> {
		return Observable.fromCallable {
			cryptoCompareCache.setFavoutite(false, symbol)
		}
	}
}