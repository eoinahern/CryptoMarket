package cryptomarket.eoinahern.ie.cryptomarket.domain.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.repository.cryptocompare.CryptoCompareRepositoryImp
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import cryptomarket.eoinahern.ie.cryptomarket.domain.repository.CryptoCompareRepository
import io.reactivex.Observable
import javax.inject.Inject

@PerScreen
class GetCryptoCompareDataInteractor @Inject constructor(private val cryptoCompareRepository: CryptoCompareRepositoryImp) : BaseInteractor<Map<String, CryptoCurrency>>() {

	override fun buildObservable(): Observable<Map<String, CryptoCurrency>> {
		return cryptoCompareRepository.getCoinData().flatMap { it.getCryptoComparedata() }
	}
}