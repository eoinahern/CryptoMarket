package cryptomarket.eoinahern.ie.cryptomarket.data.repository.news.datasource

import android.content.SharedPreferences
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.news.NewsCache
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.NEWS_SAVED_DATE
import cryptomarket.eoinahern.ie.cryptomarket.tools.date.DateUtil
import io.reactivex.Observable
import javax.inject.Inject


class NewsDataStoreFactory @Inject constructor(private val apiNewsDataStore: ApiNewsDataStore,
											   private val diskNewsDataStore: DiskNewsDataStore,
											   private val sharedPreferences: SharedPreferences,
											   private val newsCache: NewsCache,
											   private val dateUtil: DateUtil) {

	fun getDataStore(): Observable<NewsDataStore> {

		return newsCache.hasData().map {

			val dateStr = sharedPreferences.getString(NEWS_SAVED_DATE, "")

			if (it > 0 && dateUtil.checkLargerThanOneDay(dateStr)) {
				newsCache.deleteAllData()
				apiNewsDataStore
			} else if (it > 0 && !dateUtil.checkLargerThanOneDay(dateStr)) {
				diskNewsDataStore
			} else {
				apiNewsDataStore
			}

		}.toObservable()
	}
}