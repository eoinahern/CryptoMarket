package cryptomarket.eoinahern.ie.cryptomarket.data.repository.cryptocompare

import android.content.SharedPreferences
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyData
import cryptomarket.eoinahern.ie.cryptomarket.tools.date.DateUtil
import io.reactivex.Observable
import javax.inject.Inject


class ApiCryptoCompareDataStore @Inject constructor(private val cryptoApi: CryptoApi,
													private val sharedPrefsEdit: SharedPreferences.Editor,
													private val dateUtil: DateUtil) : CryptoCompareDataStore {


	override fun getCryptoComparedata(): Observable<Map<String, CryptoCurrency>> {
		return cryptoApi.getList().map { it.cryptoWrapper }
	}


}