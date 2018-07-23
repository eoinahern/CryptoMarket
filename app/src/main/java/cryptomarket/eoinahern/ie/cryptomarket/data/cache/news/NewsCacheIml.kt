package cryptomarket.eoinahern.ie.cryptomarket.data.cache

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import io.reactivex.Observable

class NewsCacheIml : NewsCache {

	override fun saveNews(newsList: List<CryptoNewsItem>) {

	}

	override fun getNews(): Observable<List<CryptoNewsItem>> {
		return Observable.fromCallable { listOf<CryptoNewsItem>() }
	}

	override fun hasData(): Boolean {
		return false
	}


}