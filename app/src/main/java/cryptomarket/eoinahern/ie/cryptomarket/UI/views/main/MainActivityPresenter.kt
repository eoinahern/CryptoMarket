package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.*
import cryptomarket.eoinahern.ie.cryptomarket.data.util.NoConnectionException
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseDisposableObserver
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseSubscriber
import cryptomarket.eoinahern.ie.cryptomarket.domain.main.GetCryptoListInteractor
import cryptomarket.eoinahern.ie.cryptomarket.domain.main.MainActivityCombinedInteractor
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@PerScreen
class MainActivityPresenter @Inject constructor(private val mainActivityCombinedInteractor: MainActivityCombinedInteractor) : BasePresenter<MainActivityView>() {


	fun getCurrencyUpdateData(currency: String) {

		mainActivityCombinedInteractor.setCurrency(currency).execute(object : BaseSubscriber<Pair<List<CoinMarketCrypto>, Map<String, CryptoCurrency>>>() {

			override fun onNext(t: Pair<List<CoinMarketCrypto>, Map<String, CryptoCurrency>>) {
				getView()?.hideLoading()
				getView()?.updateRecyclerView(t.first)
				getView()?.initCurrencyData(t.second)
			}

			override fun onSubscribe(d: Disposable) {
				mainActivityCombinedInteractor.addDisposables(d)
			}

			override fun onError(e: Throwable) {
				e.printStackTrace()

				if ((e as? NoConnectionException) != null) {
					getView()?.showNetworkError()
				} else {
					getView()?.showOtherError()
				}
			}
		})
	}

	fun navigateToDetail(symbol: String) {
		getView()?.gotToDetail(symbol)
	}

	fun persistFavourites() {
		// save map of selected faves!!
		// save to sharedPrefs
	}

	override fun detachView() {
		super.detachView()
		mainActivityCombinedInteractor.disposeObs()
	}
}