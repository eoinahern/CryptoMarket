package cryptomarket.eoinahern.ie.cryptomarket.data.repository.news.datasource

import cryptomarket.eoinahern.ie.cryptomarket.data.cache.news.NewsCache
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import io.reactivex.Observable
import javax.inject.Inject


class DiskNewsDataStore @Inject constructor(private val newsCache: NewsCache) : NewsDataStore {

	override fun getNews(): Observable<List<CryptoNewsItem>> {
		return newsCache.getNews()
	}
}