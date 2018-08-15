package cryptomarket.eoinahern.ie.cryptomarket.data.repository.cryptocompare

import android.content.SharedPreferences
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.cryptocompare.CryptoCompareCache
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyData
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.CURRENCY_DATA_SAVED_DATE
import cryptomarket.eoinahern.ie.cryptomarket.tools.date.DateUtil
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject


class ApiCryptoCompareDataStore @Inject constructor(private val cryptoApi: CryptoApi,
													private val sharedPrefsEdit: SharedPreferences.Editor,
													private val dateUtil: DateUtil,
													private val cryptoCompareCache: CryptoCompareCache) : CryptoCompareDataStore {


	override fun getCryptoComparedata(): Observable<Map<String, CryptoCurrency>> {

		return Observable.zip(cryptoApi.getList(),
				cryptoCompareCache.getFavourites(),
				BiFunction<CurrencyData, List<CryptoCurrency>, Map<String, CryptoCurrency>> { x1, x2 ->
					x2.forEach { item ->
						x1.cryptoWrapper[item.Symbol]?.Favourite = true
					}

					cryptoCompareCache.saveCryptoData(x1.cryptoWrapper.map { it.value })
					sharedPrefsEdit.putString(CURRENCY_DATA_SAVED_DATE, dateUtil.getTodaysDateStr()).apply()
					x1.cryptoWrapper
				})
	}
}