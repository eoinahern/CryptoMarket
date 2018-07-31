package cryptomarket.eoinahern.ie.cryptomarket.domain.repository

import cryptomarket.eoinahern.ie.cryptomarket.data.repository.news.datasource.NewsDataStore
import io.reactivex.Observable

interface NewsRepository {

	fun getNews(): Observable<NewsDataStore>
}