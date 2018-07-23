package cryptomarket.eoinahern.ie.cryptomarket.data.cache.news

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import io.reactivex.Observable
import io.reactivex.Single

interface NewsCache {

	fun saveNews(newsList: List<CryptoNewsItem>)

	fun getNews(): Observable<List<CryptoNewsItem>>

	fun hasData(): Single<Int>
}