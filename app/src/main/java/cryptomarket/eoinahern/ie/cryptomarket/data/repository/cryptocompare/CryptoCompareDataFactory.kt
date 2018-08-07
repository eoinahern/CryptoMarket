package cryptomarket.eoinahern.ie.cryptomarket.data.repository.cryptocompare

import android.content.SharedPreferences
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.cryptocompare.CryptoCompareCache
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.CURRENCY_DATA_SAVED_DATE
import cryptomarket.eoinahern.ie.cryptomarket.tools.date.DateUtil
import io.reactivex.Observable
import javax.inject.Inject


class CryptoCompareDataFactory @Inject constructor(private val apiCryptoCompareDataStore: ApiCryptoCompareDataStore,
												   private val diskCryptoCompareDataStore: DiskCryptoCompareDataStore,
												   private val sharedPrefs: SharedPreferences,
												   private val cryptoCompareCache: CryptoCompareCache,
												   private val dateUtil: DateUtil) {


	fun getDataStore(): Observable<CryptoCompareDataStore> {

		return cryptoCompareCache.countRows().map {

			val dateStr = sharedPrefs.getString(CURRENCY_DATA_SAVED_DATE, "")

			if (dateStr.isEmpty())
				apiCryptoCompareDataStore


			if (it > 0 && !dateUtil.checkLargerThanTwoDays(dateStr)) {
				diskCryptoCompareDataStore
			} else {
				apiCryptoCompareDataStore
			}

		}.toObservable()
	}
}