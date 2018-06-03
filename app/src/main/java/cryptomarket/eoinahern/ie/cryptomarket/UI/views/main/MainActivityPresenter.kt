package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.*
import cryptomarket.eoinahern.ie.cryptomarket.data.util.NoConnectionException
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseDisposableObserver
import cryptomarket.eoinahern.ie.cryptomarket.domain.main.GetCryptoListInteractor
import javax.inject.Inject

@PerScreen
class MainActivityPresenter @Inject constructor(private val getCryptoListInteractor: GetCryptoListInteractor) : BasePresenter<MainActivityView>() {

	fun getCurrencyData(offset: Int = 0, limit: Int = 50) {

		getCryptoListInteractor.setStartLimit(offset, limit).execute(object : BaseDisposableObserver<List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>>>() {

			override fun onNext(dataList: List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>>) {

				getView()?.hideLoading()
				println("size : ${dataList.size}")
				getView()?.updateRecyclerView(dataList)
			}

			override fun onError(e: Throwable) {

				e.printStackTrace()
				/**
				 * differenciate error types
				 */

				if (e is NoConnectionException) {
					getView()?.hideLoading()
					getView()?.showError()
				} else {
					getView()?.hideLoading()
					getView()?.showError()
				}
			}
		})
	}

	fun navigateToDetail() {

	}

	override fun detachView() {
		super.detachView()
		getCryptoListInteractor.disposeObs()
	}
}