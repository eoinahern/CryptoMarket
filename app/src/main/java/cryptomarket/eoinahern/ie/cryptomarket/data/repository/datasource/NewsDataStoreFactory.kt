package cryptomarket.eoinahern.ie.cryptomarket.data.repository.datasource

import android.content.SharedPreferences
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.news.NewsCache
import io.reactivex.Observable
import javax.inject.Inject


class NewsDataStoreFactory @Inject constructor(private val apiNewsDataStore: ApiNewsDataStore,
											   private val diskNewsDataStore: DiskNewsDataStore,
											   private val sharedPreferences: SharedPreferences,
											   private val newsCache: NewsCache) {

	fun getDataStore(): Observable<NewsDataStore> {

		return newsCache.hasData().map {
			if (it > 0) {
				diskNewsDataStore
			} else {
				apiNewsDataStore
			}
		}.toObservable()
	}
}