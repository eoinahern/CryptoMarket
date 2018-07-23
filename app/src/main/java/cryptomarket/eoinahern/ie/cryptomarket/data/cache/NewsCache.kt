package cryptomarket.eoinahern.ie.cryptomarket.data.cache

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import io.reactivex.Observable

interface NewsCache {

	fun saveNews(newsList: List<CryptoNewsItem>)

	fun getNews(): Observable<List<CryptoNewsItem>>

	fun hasData(): Boolean
}