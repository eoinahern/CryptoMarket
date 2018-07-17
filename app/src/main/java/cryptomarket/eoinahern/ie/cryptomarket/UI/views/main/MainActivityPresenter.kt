package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.*
import cryptomarket.eoinahern.ie.cryptomarket.data.util.NoConnectionException
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseDisposableObserver
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseSubscriber
import cryptomarket.eoinahern.ie.cryptomarket.domain.main.GetCryptoListInteractor
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@PerScreen
class MainActivityPresenter @Inject constructor(private var getCryptoListInteractor: GetCryptoListInteractor) : BasePresenter<MainActivityView>() {


	fun getCurrencyDataInitial() {

		getCryptoListInteractor.execute(object : BaseDisposableObserver<Pair<List<CoinMarketCrypto>, CurrencyData>>() {

			override fun onNext(t: Pair<List<CoinMarketCrypto>, CurrencyData>) {
				getView()?.hideLoading()
				getView()?.updateRecyclerView(t.first)
				getView()?.initCurrencyData(t.second.cryptoWrapper)
			}

			override fun onError(e: Throwable) {
				e.printStackTrace()
				println(e.message)

				if (e as? NoConnectionException != null) {
					getView()?.showNetworkError()
				} else {
					getView()?.showOtherError()
				}
			}
		})
	}

	/*fun getCurrencyUpdateData(offset: Int, limit: Int) {

		getCryptoListInteractor.setStartLimit(offset, limit).execute(object : BaseSubscriber<List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>?>>() {

			override fun onNext(t: List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>?>) {
				getView()?.updateRecyclerView(t)
			}

			override fun onSubscribe(d: Disposable) {
				getCryptoListInteractor.addDisposables(d)
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
	}*/

	fun navigateToDetail(symbol: String) {
		getView()?.gotToDetail(symbol)
	}

	override fun detachView() {
		super.detachView()
		getCryptoListInteractor.disposeObs()
	}
}