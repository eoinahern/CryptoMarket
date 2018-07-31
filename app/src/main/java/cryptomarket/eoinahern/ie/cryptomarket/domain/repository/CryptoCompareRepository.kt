package cryptomarket.eoinahern.ie.cryptomarket.domain.repository

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyData
import cryptomarket.eoinahern.ie.cryptomarket.data.repository.cryptocompare.CryptoCompareDataStore
import io.reactivex.Observable


interface CryptoCompareRepository {

	fun getCoinData(): Observable<CryptoCompareDataStore>
}