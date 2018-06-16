package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseSubscriber
import cryptomarket.eoinahern.ie.cryptomarket.domain.details.GetGraphDataInteractor
import javax.inject.Inject

@PerScreen
class DetailsActivityPresenter @Inject constructor(private val getGraphDataInteractor: GetGraphDataInteractor) : BasePresenter<DetailsView>() {

	fun loadSingleCryptoData() {

		getGraphDataInteractor.execute(object : BaseSubscriber<String>() {
			override fun onNext(t: String) {

				getView()?.hideLoading()

			}

			override fun onError(e: Throwable) {

				getView()?.hideLoading()


			}
		})
	}
}