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

		getCryptoListInteractor.execute(object : BaseDisposableObserver<List<CoinMarketCrypto>>() {

			override fun onNext(t: List<CoinMarketCrypto>) {
				getView()?.hideLoading()
				println("num elements ${t.size}")
				println("last name = ${t[t.size - 1].name}")
				getView()?.updateRecyclerView(t)
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

	fun navigateToDetail(crypto: CryptoCurrency?, currencyFullPriceDataDisplay: CurrencyFullPriceDataDisplay?) {
		getView()?.gotToDetail(crypto, currencyFullPriceDataDisplay)
	}

	override fun detachView() {
		super.detachView()
		getCryptoListInteractor.disposeObs()
	}
}