package cryptomarket.eoinahern.ie.cryptomarket.data.repository.cryptocompare

import cryptomarket.eoinahern.ie.cryptomarket.domain.repository.CryptoCompareRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoCompareRepositoryImp @Inject constructor(private val cryptoCompareDataFactory: CryptoCompareDataFactory) : CryptoCompareRepository {

	override fun getCoinData(): Observable<CryptoCompareDataStore> {
		return  cryptoCompareDataFactory.getDataStore()
	}


}