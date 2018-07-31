package cryptomarket.eoinahern.ie.cryptomarket.data.cache.cryptocompare

import cryptomarket.eoinahern.ie.cryptomarket.data.cache.CryptoDatabase
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import io.reactivex.Observable


class CryptoCompareCacheImp constructor(private val cryptoDataBase: CryptoDatabase) : CryptoCompareCache {

	override fun saveCryptoData(list: List<CryptoCurrency>) {
		cryptoDataBase.cryptoCompareDao().insertCurrency(list)
	}

	override fun getCryptoMap(): Observable<Map<String, CryptoCurrency>> {
		return cryptoDataBase.cryptoCompareDao().getAll().map { it.associate { it.Symbol to it } }
				.toObservable()
	}

	override fun deleteAll() {
		cryptoDataBase.cryptoCompareDao().deleteAll()
	}

}