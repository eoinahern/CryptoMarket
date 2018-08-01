package cryptomarket.eoinahern.ie.cryptomarket.domain.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CoinMarketCrypto
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject


@PerScreen
class MainActivityCombinedInteractor @Inject constructor(private val getCryptoCompareDataInteractor: GetCryptoCompareDataInteractor,
														 private val getCryptoListInteractor: GetCryptoListInteractor) : BaseInteractor<Pair<List<CoinMarketCrypto>, Map<String, CryptoCurrency>>>() {

	private lateinit var currency: String

	fun setCurrency(currency: String): MainActivityCombinedInteractor {
		this.currency = currency
		return this
	}

	override fun buildObservable(): Observable<Pair<List<CoinMarketCrypto>, Map<String, CryptoCurrency>>> {
		return Observable.zip(getCryptoListInteractor.setCurrency(currency).buildObservable(),
				getCryptoCompareDataInteractor.buildObservable(),
				BiFunction<List<CoinMarketCrypto>, Map<String, CryptoCurrency>,
						Pair<List<CoinMarketCrypto>, Map<String, CryptoCurrency>>> { z1, z2 ->
					Pair(z1, z2)
				})
	}
}