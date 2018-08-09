package cryptomarket.eoinahern.ie.cryptomarket.UI.views.favourites

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseSubscriber
import cryptomarket.eoinahern.ie.cryptomarket.domain.favourites.GetFavouritesInteractor
import javax.inject.Inject


@PerScreen
class FavouritesActivityPresenter @Inject constructor(private val getFavouritesInteractor: GetFavouritesInteractor) : BasePresenter<FavouritesActivityView>() {

	fun getFavourites() {

		getFavouritesInteractor.execute(object : BaseSubscriber<List<CryptoCurrency>>() {
			override fun onNext(t: List<CryptoCurrency>) {
				getView()?.hideLoading()
			}

			override fun onError(e: Throwable) {

			}
		})

	}
}