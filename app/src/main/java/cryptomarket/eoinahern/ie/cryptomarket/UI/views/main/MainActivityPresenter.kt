package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyData
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyPriceConversions
import cryptomarket.eoinahern.ie.cryptomarket.data.util.NoConnectionException
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseDisposableObserver
import cryptomarket.eoinahern.ie.cryptomarket.domain.main.GetCryptoListInteractor
import javax.inject.Inject

@PerScreen
class MainActivityPresenter @Inject constructor(private val getCryptoListInteractor: GetCryptoListInteractor) : BasePresenter<MainActivityView>() {

	fun getCurrencyData(offset: Int = 0, limit: Int = 50) {

		getCryptoListInteractor.setStartLimit(offset, limit).execute(object : BaseDisposableObserver<List<Pair<CryptoCurrency?, CurrencyPriceConversions?>>>() {

			override fun onNext(dataMap: List<Pair<CryptoCurrency?, CurrencyPriceConversions?>>) {

				getView()?.hideLoading()
				println(dataMap.toString())
				getView()?.updateRecyclerView(dataMap)
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