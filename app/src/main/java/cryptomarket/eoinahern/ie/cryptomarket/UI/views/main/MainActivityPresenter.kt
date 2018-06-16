package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import android.support.v4.content.ContextCompat.startActivity
import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail.DetailsActivity
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

		getCryptoListInteractor.setStartLimit(0, 50).execute(object : BaseDisposableObserver<List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>?>> () {

			override fun onNext(t: List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>?>) {
				getView()?.hideLoading()
				getView()?.updateRecyclerView(t)
			}

			override fun onError(e: Throwable) {
				e.printStackTrace()
				println("error caught")
				println(e.message)

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

	fun getCurrencyUpdateData(offset: Int, limit: Int) {

		getCryptoListInteractor.setStartLimit(offset, limit).execute(object : BaseSubscriber<List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>?>>() {

			override fun onNext(t: List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>?>) {
				getView()?.updateRecyclerView(t)
			}

			override fun onSubscribe(d: Disposable) {
				getCryptoListInteractor.addDisposables(d)
			}

			override fun onError(e: Throwable) {
				e.printStackTrace()

				if (e is NoConnectionException) {
					getView()?.showError()
				} else {
					getView()?.showError()
				}
			}
		})
	}

	fun navigateToDetail(crypto : CryptoCurrency?) {
		getView()?.gotToDetail(crypto)
	}

	override fun detachView() {
		super.detachView()
		getCryptoListInteractor.disposeObs()
	}
}