package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.*
import cryptomarket.eoinahern.ie.cryptomarket.data.util.NoConnectionException
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseDisposableObserver
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseSubscriber
import cryptomarket.eoinahern.ie.cryptomarket.domain.main.GetCryptoListInteractor
import cryptomarket.eoinahern.ie.cryptomarket.domain.main.MainActivityCombinedInteractor
import cryptomarket.eoinahern.ie.cryptomarket.domain.main.SaveFavouritesDataInteractor
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@PerScreen
class MainActivityPresenter @Inject constructor(private val mainActivityCombinedInteractor: MainActivityCombinedInteractor,
												private val saveFavouritesDataInteractor: SaveFavouritesDataInteractor) : BasePresenter<MainActivityView>() {

	private var currencyData: MutableMap<String, CryptoCurrency> = mutableMapOf()

	fun getCurrencyUpdateData(currency: String) {

		mainActivityCombinedInteractor.setCurrency(currency).execute(object : BaseSubscriber<Pair<List<CoinMarketCrypto>, Map<String, CryptoCurrency>>>() {

			override fun onNext(t: Pair<List<CoinMarketCrypto>, Map<String, CryptoCurrency>>) {
				getView()?.hideLoading()
				getView()?.updateRecyclerView(t.first)
				updateCurrencyMap(t.second)
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

	private fun updateCurrencyMap(currency: Map<String, CryptoCurrency>) {
		currencyData.clear()
		currencyData.putAll(currency)
	}

	fun getCurrencyMapItem(key: String) = currencyData[key]

	fun updateCurrencyFavourite(key: String, state: Boolean) {
		currencyData[key]?.let { item ->
			item.Favourite = state
			currencyData[key] = item
		}
	}

	fun getSelected(key: String): Boolean = currencyData[key]?.Favourite == true

	fun navigateToDetail(symbol: String) {
		getView()?.gotToDetail(symbol)
	}

	fun persistFavourites() {
		saveFavouritesDataInteractor.init(currencyData).execute(object : BaseSubscriber<Unit>() {
			override fun onError(e: Throwable) {
			}

			override fun onNext(t: Unit) {
				println("save complete!!")
			}
		})
	}

	override fun detachView() {
		super.detachView()
		mainActivityCombinedInteractor.disposeObs()
	}
}