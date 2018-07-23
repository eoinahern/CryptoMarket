package cryptomarket.eoinahern.ie.cryptomarket.data.cache

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import dagger.Provides
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsCacheIml @Inject constructor() : NewsCache {

	override fun saveNews(newsList: List<CryptoNewsItem>) {

	}

	override fun getNews(): Observable<List<CryptoNewsItem>> {
		return Observable.fromCallable { listOf<CryptoNewsItem>() }
	}

	override fun hasData(): Boolean {
		return false
	}


}