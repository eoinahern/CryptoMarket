package cryptomarket.eoinahern.ie.cryptomarket.data.cache.cryptocompare

import android.arch.persistence.room.Insert
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import io.reactivex.Observable


interface CryptoCompareCache {

	fun saveCryptoData(list: List<CryptoCurrency>)

	fun getCryptoMap(): Observable<Map<String, CryptoCurrency>>

	fun deleteAll()
}