package cryptomarket.eoinahern.ie.cryptomarket.data.repository.news.datasource

import cryptomarket.eoinahern.ie.cryptomarket.data.repository.news.datasource.NewsDataStore
import cryptomarket.eoinahern.ie.cryptomarket.data.repository.news.datasource.NewsDataStoreFactory
import cryptomarket.eoinahern.ie.cryptomarket.domain.repository.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImp @Inject constructor(private val newsDataStoreFactory: NewsDataStoreFactory) : NewsRepository {

	override fun getNews(): Observable<NewsDataStore> {
		return newsDataStoreFactory.getDataStore()
	}
}