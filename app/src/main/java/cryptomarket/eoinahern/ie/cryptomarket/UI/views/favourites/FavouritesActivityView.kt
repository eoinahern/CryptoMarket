package cryptomarket.eoinahern.ie.cryptomarket.UI.views.favourites

import cryptomarket.eoinahern.ie.cryptomarket.UI.base.MvpView

interface FavouritesActivityView : MvpView {

	fun showLoading()

	fun hideLoading()

	fun showFavourites()

	fun errorLoadingFavourites()

	fun onDeleteComplete(position: Int)

	fun onDeleteFailed()

	fun showEmpty()
}