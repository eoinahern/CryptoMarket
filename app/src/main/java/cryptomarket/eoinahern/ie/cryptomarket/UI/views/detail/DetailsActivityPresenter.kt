package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.*
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

	fun loadDetailsData(cryptoAbv: String, toCurrency: String, id: String) {

		getGraphDataInteractor.setSearchCrypto(cryptoAbv, toCurrency)
				.execute(object : BaseSubscriber<MutableList<Response<HistoricalData>>>() {

					override fun onNext(t: MutableList<Response<HistoricalData>>) {
						t.forEach {
							if (it.code() != 200) {
								getView()?.showNetworkError()
								return
							}
						}

						getView()?.initGraphData(t.map { it.body() }.toMutableList())
						getCoinInfo(id, cryptoAbv, toCurrency)
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

	private fun getCoinInfo(id: String, cryptoAbv: String, toCurrency: String) {

		getCryptoInfoInteractor.setID(id, cryptoAbv, toCurrency).execute(object : BaseSubscriber<Pair<SnapShotData,
				CurrencyFullPriceDataDisplay?>>() {
			override fun onNext(data: Pair<SnapShotData, CurrencyFullPriceDataDisplay?>) {
				getView()?.hideLoading()
				getView()?.showGeneralCoinInfo(data.first)
				getView()?.showFullPriceData(data.second)
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