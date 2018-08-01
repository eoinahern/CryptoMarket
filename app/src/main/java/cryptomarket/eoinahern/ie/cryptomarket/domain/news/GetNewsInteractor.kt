package cryptomarket.eoinahern.ie.cryptomarket.domain.news

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import cryptomarket.eoinahern.ie.cryptomarket.data.repository.news.datasource.NewsRepositoryImp
import cryptomarket.eoinahern.ie.cryptomarket.domain.repository.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject

@PerScreen
class GetNewsInteractor @Inject constructor(private val newsRepository: NewsRepositoryImp) : BaseInteractor<List<CryptoNewsItem>>() {

	override fun buildObservable(): Observable<List<CryptoNewsItem>> {
		return newsRepository.getNews().flatMap { it.getNews() }
	}
}