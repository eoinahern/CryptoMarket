package cryptomarket.eoinahern.ie.cryptomarket.data.repository.datasource

import android.content.SharedPreferences
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.NewsCache
import javax.inject.Inject


class NewsDataStoreFactory @Inject constructor(private val apiNewsDataStore: ApiNewsDataStore,
											   private val diskNewsDataStore: DiskNewsDataStore,
											   private val sharedPreferences: SharedPreferences,
											   private val newsCache: NewsCache) {


	fun getDataStore(): NewsDataStore {

		if (newsCache.hasData()) {
			return diskNewsDataStore
		}

		return apiNewsDataStore
	}
}