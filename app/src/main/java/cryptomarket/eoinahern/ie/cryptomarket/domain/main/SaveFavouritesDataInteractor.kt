package cryptomarket.eoinahern.ie.cryptomarket.domain.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.cryptocompare.CryptoCompareCache
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject


@PerScreen
class SaveFavouritesDataInteractor @Inject constructor(private val cryptoCompareCache: CryptoCompareCache) : BaseInteractor<Unit>() {

	private var currencyData: MutableMap<String, CryptoCurrency> = mutableMapOf()

	fun init(currencyIn: Map<String, CryptoCurrency>): SaveFavouritesDataInteractor {
		currencyData.clear()
		currencyData.putAll(currencyIn)
		return this
	}

	override fun buildObservable(): Observable<Unit> {
		return Observable.fromCallable {
			cryptoCompareCache.deleteAll()
			cryptoCompareCache.saveCryptoData(currencyData.map { it.value })
			Unit
		}
	}
}