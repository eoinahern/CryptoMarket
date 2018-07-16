package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CoinFullSnapShot
import cryptomarket.eoinahern.ie.cryptomarket.data.models.GeneralCoinInfo
import cryptomarket.eoinahern.ie.cryptomarket.data.models.HistoricalData
import cryptomarket.eoinahern.ie.cryptomarket.data.models.SnapShotData
import cryptomarket.eoinahern.ie.cryptomarket.data.util.NoConnectionException
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseSubscriber
import cryptomarket.eoinahern.ie.cryptomarket.domain.details.GetCryptoInfoInteractor
import cryptomarket.eoinahern.ie.cryptomarket.domain.details.GetGraphDataInteractor
import io.reactivex.disposables.Disposable
import retrofit2.Response
import javax.inject.Inject

@PerScreen
class DetailsActivityPresenter @Inject constructor(private val getGraphDataInteractor: GetGraphDataInteractor,
												   private val getCryptoInfoInteractor: GetCryptoInfoInteractor) : BasePresenter<DetailsView>() {

	fun loadDetailsData(cryptoAbv: String, covertedTo: String, id: String) {

		getGraphDataInteractor.setSearchCrypto(cryptoAbv, covertedTo)
				.execute(object : BaseSubscriber<MutableList<Response<HistoricalData>>>() {

					override fun onNext(t: MutableList<Response<HistoricalData>>) {
						t.forEach {
							if (it.code() != 200) {
								getView()?.showNetworkError()
								return
							}
						}

						getView()?.initGraphData(t.map { it.body() }.toMutableList())
						getCoinInfo(id)
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

	private fun getCoinInfo(id: String) {

		getCryptoInfoInteractor.setID(id).execute(object : BaseSubscriber<SnapShotData>() {
			override fun onNext(snapShotData: SnapShotData) {
				getView()?.hideLoading()
				getView()?.showGeneralCoinInfo(snapShotData)
			}

			override fun onError(e: Throwable) {
				if (e is NoConnectionException) {
					getView()?.showNetworkError()
				} else {
					getView()?.showOtherError()
				}
			}

			override fun onSubscribe(d: Disposable) {
				getCryptoInfoInteractor.addDisposables(d)
			}
		})
	}

	override fun detachView() {
		super.detachView()
		getGraphDataInteractor.disposeObs()
		getCryptoInfoInteractor.disposeObs()
	}
}