package cryptomarket.eoinahern.ie.cryptomarket.domain.news

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoNewsApi
import cryptomarket.eoinahern.ie.cryptomarket.data.repository.NewsRepositoryImp
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

@PerScreen
class GetNewsInteractor @Inject constructor(private val newsRepositoryImp: NewsRepositoryImp) : BaseInteractor<List<CryptoNewsItem>>() {//private val cryptoNewsApi: CryptoNewsApi,
	//@Named("newsKey") private val cryptoKey: String) : BaseInteractor<List<CryptoNewsItem>>() {

	override fun buildObservable(): Observable<List<CryptoNewsItem>> {

		return newsRepositoryImp.getNews()
		//return cryptoNewsApi.getNews(cryptoKey)
	}
}