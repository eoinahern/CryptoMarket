package cryptomarket.eoinahern.ie.cryptomarket.domain.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

@PerScreen
class GetCryptoCompareDataInteractor @Inject constructor(private val cryptoApi: CryptoApi) : BaseInteractor<Map<String, CryptoCurrency>>() {

	override fun buildObservable(): Observable<Map<String, CryptoCurrency>> {
		return cryptoApi.getList().map { it.cryptoWrapper }
	}
}