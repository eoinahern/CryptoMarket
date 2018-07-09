package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.HistoricalData
import cryptomarket.eoinahern.ie.cryptomarket.data.util.NoConnectionException
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseSubscriber
import cryptomarket.eoinahern.ie.cryptomarket.domain.details.GetGraphDataInteractor
import io.reactivex.disposables.Disposable
import retrofit2.Response
import javax.inject.Inject

@PerScreen
class DetailsActivityPresenter @Inject constructor(private val getGraphDataInteractor: GetGraphDataInteractor) : BasePresenter<DetailsView>() {

	fun loadGraphData(cryptoAbv: String, covertedTo: String) {

		getGraphDataInteractor.setSearchCrypto(cryptoAbv, covertedTo)
				.execute(object : BaseSubscriber<MutableList<Response<HistoricalData>>>() {

					override fun onNext(t: MutableList<Response<HistoricalData>>) {
						t.forEach {
							if (it.code() != 200) {
								getView()?.showNetworkError()
								return
							}
						}

						getView()?.hideLoading()
						getView()?.initGraphData(t.map { it.body() }.toMutableList())
					}

					override fun onError(e: Throwable) {
						if (e is NoConnectionException) {
							getView()?.showNetworkError()
						} else {
							getView()?.showOtherError()
						}
					}

					override fun onSubscribe(d: Disposable) {
						getGraphDataInteractor.addDisposables(d)
					}
				})
	}

	override fun detachView() {
		super.detachView()
		getGraphDataInteractor.disposeObs()
	}
}