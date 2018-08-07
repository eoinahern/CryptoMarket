package cryptomarket.eoinahern.ie.cryptomarket.data.repository.news.datasource

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import io.reactivex.Observable

interface NewsDataStore {

	fun getNews(): Observable<List<CryptoNewsItem>>
}