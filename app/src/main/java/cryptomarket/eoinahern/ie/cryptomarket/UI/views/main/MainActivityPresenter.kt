package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyData
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyPriceConversions
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseDisposableObserver
import cryptomarket.eoinahern.ie.cryptomarket.domain.main.GetCryptoListInteractor
import javax.inject.Inject

@PerScreen
class MainActivityPresenter @Inject constructor( val getCryptoListInteractor : GetCryptoListInteractor) : BasePresenter<MainActivityView>() {

	fun getCurrencyData(offset : Int = 0, limit : Int = 50) {

		getCryptoListInteractor.setStartLimir(offset,limit).execute(object : BaseDisposableObserver<HashMap<String, Pair<CryptoCurrency?, CurrencyPriceConversions?>>>() {

			override fun onNext(dataMap: HashMap<String, Pair<CryptoCurrency?, CurrencyPriceConversions?>>) {

				getView()?.hideLoading()
				println(dataMap.values.toString())
				getView()?.updateRecyclerView(dataMap)
			}

			override fun onError(e: Throwable) {

				e.printStackTrace()
				println(e.localizedMessage)
				println(e.message)
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