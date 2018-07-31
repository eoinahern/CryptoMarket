package cryptomarket.eoinahern.ie.cryptomarket.domain.news

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import cryptomarket.eoinahern.ie.cryptomarket.data.repository.news.datasource.NewsRepositoryImp
import io.reactivex.Observable
import javax.inject.Inject

@PerScreen
class GetNewsInteractor @Inject constructor(private val newsRepositoryImp: NewsRepositoryImp) : BaseInteractor<List<CryptoNewsItem>>() {

	override fun buildObservable(): Observable<List<CryptoNewsItem>> {
		return newsRepositoryImp.getNews().flatMap { it.getNews() }
	}
}