package cryptomarket.eoinahern.ie.cryptomarket.domain.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyData
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

@PerScreen
class GetCryptoListInteractor @Inject constructor(val cryptoApi : CryptoApi ) : BaseInteractor<CurrencyData>() {

	override fun buildObservable(): Observable<CurrencyData> {
		return cryptoApi.getList()
	}
}