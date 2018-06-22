package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.HistoricalData
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseSubscriber
import cryptomarket.eoinahern.ie.cryptomarket.domain.details.GetGraphDataInteractor
import retrofit2.Response
import javax.inject.Inject

@PerScreen
class DetailsActivityPresenter @Inject constructor(private val getGraphDataInteractor: GetGraphDataInteractor) : BasePresenter<DetailsView>() {

	fun loadSingleCryptoData(cryptoAbv: String, covertedTo: String) {

		getGraphDataInteractor.setSearchCrypto(cryptoAbv, covertedTo)
				.execute(object : BaseSubscriber<List<Response<HistoricalData>>>() {

					override fun onNext(t: List<Response<HistoricalData>>) {

						getView()?.hideLoading()

						t.forEach {
							if(it.code() != 200) {
								getView()?.showError()
								return
							}
						}


						println(t.toString())
						getView()?.DisplayGraphData(t.map{ it.body()})
					}


					override fun onError(e: Throwable) {
						getView()?.hideLoading()
						getView()?.showError()
					}
				})
	}
}