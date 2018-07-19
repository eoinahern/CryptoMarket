package cryptomarket.eoinahern.ie.cryptomarket.UI.views.news

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import cryptomarket.eoinahern.ie.cryptomarket.data.util.NoConnectionException
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseSubscriber
import cryptomarket.eoinahern.ie.cryptomarket.domain.news.GetNewsInteractor
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@PerScreen
class NewsActivityPresenter @Inject constructor(private val getNewsInteractor: GetNewsInteractor) : BasePresenter<NewsView>() {

	fun getNews() {

		getNewsInteractor.execute(object : BaseSubscriber<List<CryptoNewsItem>>() {
			override fun onNext(t: List<CryptoNewsItem>) {
				getView()?.hideLoading()
				getView()?.updateNews(t)
			}

			override fun onError(e: Throwable) {
				if (e is NoConnectionException) {
					getView()?.showConnectionError()
				} else {
					getView()?.showOtherError()
				}
			}

			override fun onSubscribe(d: Disposable) {
				getNewsInteractor.addDisposables(d)
			}
		})

	}

	override fun detachView() {
		super.detachView()
		getNewsInteractor.disposeObs()
	}
}