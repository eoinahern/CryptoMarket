package cryptomarket.eoinahern.ie.cryptomarket.data.cache.cryptocompare

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import io.reactivex.Observable
import io.reactivex.Single


interface CryptoCompareCache {

	fun saveCryptoData(list: List<CryptoCurrency>)

	fun getCryptoMap(): Observable<Map<String, CryptoCurrency>>

	fun deleteAll()

	fun countRows(): Single<Int>

	fun getFavourites(): Observable<List<CryptoCurrency>>

	fun setFavoutite(state: Boolean, symbol: String)
}