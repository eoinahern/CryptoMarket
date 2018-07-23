package cryptomarket.eoinahern.ie.cryptomarket.data.repository.datasource

import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoNewsApi
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.news.NewsCache
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named


class ApiNewsDataStore @Inject constructor(private val newsApi: CryptoNewsApi,
										   @Named("newsKey") private val apiKey: String,
										   private val newsCache: NewsCache) : NewsDataStore {

	override fun getNews(): Observable<List<CryptoNewsItem>> {
		return newsApi.getNews(apiKey).doOnNext {
			newsCache.saveNews(it)
		}
	}
}