package cryptomarket.eoinahern.ie.cryptomarket.domain.details

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

@PerScreen
class GetGraphDataInteractor @Inject constructor(private val cryptoApi: CryptoApi): BaseInteractor<String>() {

	override fun buildObservable(): Observable<String> {
		return Observable.just("hello")
	}


}