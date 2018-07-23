package cryptomarket.eoinahern.ie.cryptomarket.data.cache.news

import cryptomarket.eoinahern.ie.cryptomarket.data.cache.CryptoDatabase
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import io.reactivex.Observable

class NewsCacheIml constructor(private val cryptoDatabase: CryptoDatabase) : NewsCache {

	override fun saveNews(newsList: List<CryptoNewsItem>) {
		cryptoDatabase.newsDao().insertNewsList(newsList)
	}

	override fun getNews(): Observable<List<CryptoNewsItem>> {
		return Observable.fromCallable {
			cryptoDatabase.newsDao().getAllNewsData()
		}
	}

	override fun hasData(): Boolean {
		return cryptoDatabase.newsDao().countRows() > 0
	}
}