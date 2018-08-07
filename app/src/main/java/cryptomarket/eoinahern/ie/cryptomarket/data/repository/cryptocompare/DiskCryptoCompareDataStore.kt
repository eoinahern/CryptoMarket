package cryptomarket.eoinahern.ie.cryptomarket.data.repository.cryptocompare

import cryptomarket.eoinahern.ie.cryptomarket.data.cache.cryptocompare.CryptoCompareCache
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import io.reactivex.Observable
import javax.inject.Inject

class DiskCryptoCompareDataStore @Inject constructor(private val cryptoCompareCache: CryptoCompareCache) : CryptoCompareDataStore {

	override fun getCryptoComparedata(): Observable<Map<String, CryptoCurrency>> = cryptoCompareCache.getCryptoMap()
}