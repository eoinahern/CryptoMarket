package cryptomarket.eoinahern.ie.cryptomarket.data.repository.datasource

import android.content.SharedPreferences
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoNewsApi
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.news.NewsCache
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.NEWS_SAVED_DATE
import cryptomarket.eoinahern.ie.cryptomarket.tools.date.DateUtil
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named


class ApiNewsDataStore @Inject constructor(private val newsApi: CryptoNewsApi,
										   @Named("newsKey") private val apiKey: String,
										   private val newsCache: NewsCache,
										   private val sharedPrefsEdit: SharedPreferences.Editor,
										   private val dateUtil: DateUtil) : NewsDataStore {

	override fun getNews(): Observable<List<CryptoNewsItem>> {
		return newsApi.getNews(apiKey).flatMap {
			newsCache.saveNews(it)
			sharedPrefsEdit.putString(NEWS_SAVED_DATE, dateUtil.getTodaysDateStr())
			Observable.just(it)
		}
	}
}