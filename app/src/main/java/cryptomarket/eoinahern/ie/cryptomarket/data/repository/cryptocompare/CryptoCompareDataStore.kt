package cryptomarket.eoinahern.ie.cryptomarket.data.repository.cryptocompare

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyData
import io.reactivex.Observable


interface CryptoCompareDataStore {

	fun getCryptoComparedata(): Observable<Map<String, CryptoCurrency>>
}