package cryptomarket.eoinahern.ie.cryptomarket.domain.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyData
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

@PerScreen
class GetCryptoListInteractor @Inject constructor(private val cryptoApi : CryptoApi ) : BaseInteractor<Pair<CurrencyData, CryptoCurrency>>() {

	override fun buildObservable(): Observable<Pair<CurrencyData, CryptoCurrency>> {
		return Observable.zip(cryptoApi.getList(), cryptoApi.getPricing(),
				BiFunction<CurrencyData,CryptoCurrency, Pair<CurrencyData,CryptoCurrency>>{ a, b -> Pair(a,b)})
	}

}