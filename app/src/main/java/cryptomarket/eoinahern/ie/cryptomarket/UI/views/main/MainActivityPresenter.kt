package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyData
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseDisposableObserver
import cryptomarket.eoinahern.ie.cryptomarket.domain.main.GetCryptoListInteractor
import javax.inject.Inject

@PerScreen
class MainActivityPresenter @Inject constructor( val getCryptoListInteractor : GetCryptoListInteractor) : BasePresenter<MainActivityView>() {

	fun getCurrencyData() {

		getCryptoListInteractor.execute(object : BaseDisposableObserver<Pair<CurrencyData,CryptoCurrency>>() {

			override fun onNext(t: Pair<CurrencyData, CryptoCurrency>) {
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}

			/*override fun onNext(t: CurrencyData) {

				println(t.toString())
				getView()?.hideLoading()
				getView()?.updateRecyclerView(t.toString())
			}*/

			override fun onError(e: Throwable) {

				e.printStackTrace()
				getView()?.hideLoading()
				getView()?.showError()
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