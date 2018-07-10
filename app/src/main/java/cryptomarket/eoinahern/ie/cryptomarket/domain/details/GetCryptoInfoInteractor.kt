package cryptomarket.eoinahern.ie.cryptomarket.domain.details

import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject


class GetCryptoInfoInteractor @Inject constructor(cryptoApi : CryptoApi) : BaseInteractor<String>() {



	override fun buildObservable(): Observable<String> {
        return Observable.just("")
	}
}
