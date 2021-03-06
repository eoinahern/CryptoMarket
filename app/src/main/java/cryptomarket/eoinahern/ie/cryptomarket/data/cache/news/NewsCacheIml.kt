package cryptomarket.eoinahern.ie.cryptomarket.data.cache.news

import cryptomarket.eoinahern.ie.cryptomarket.data.cache.CryptoDatabase
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import io.reactivex.Observable
import io.reactivex.Single

class NewsCacheIml constructor(private val cryptoDatabase: CryptoDatabase) : NewsCache {

	override fun saveNews(newsList: List<CryptoNewsItem>) {

		try {
			cryptoDatabase.newsDao().insertNewsList(newsList)
		} catch (e: Exception) {
			println("error occured saving??")
		}
	}

	override fun deleteAllData() {
		cryptoDatabase.newsDao().deleteAllNewsData()
	}

	override fun getNews(): Observable<List<CryptoNewsItem>> {
		return cryptoDatabase.newsDao().getAllNewsData().toObservable()
	}

	override fun hasData(): Single<Int> {
		return cryptoDatabase.newsDao().countRows()
	}
}