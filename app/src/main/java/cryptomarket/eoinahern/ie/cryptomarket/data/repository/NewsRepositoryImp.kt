package cryptomarket.eoinahern.ie.cryptomarket.data.repository

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import cryptomarket.eoinahern.ie.cryptomarket.data.repository.datasource.NewsDataStoreFactory
import cryptomarket.eoinahern.ie.cryptomarket.domain.repository.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImp @Inject constructor(private val newsDataStoreFactory: NewsDataStoreFactory) : NewsRepository {

	override fun getNews(): Observable<List<CryptoNewsItem>> {
		return newsDataStoreFactory.getDataStore().getNews()
	}
}