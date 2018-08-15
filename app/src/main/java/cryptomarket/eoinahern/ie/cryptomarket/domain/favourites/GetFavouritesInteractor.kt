package cryptomarket.eoinahern.ie.cryptomarket.domain.favourites

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.cryptocompare.CryptoCompareCache
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

@PerScreen
class GetFavouritesInteractor @Inject constructor(private val cryptoCompareCache: CryptoCompareCache) : BaseInteractor<List<CryptoCurrency>>() {

	override fun buildObservable(): Observable<List<CryptoCurrency>> {
		return cryptoCompareCache.getFavourites()
	}
}