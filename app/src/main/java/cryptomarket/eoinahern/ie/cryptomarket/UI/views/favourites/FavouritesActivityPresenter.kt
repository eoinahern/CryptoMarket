package cryptomarket.eoinahern.ie.cryptomarket.UI.views.favourites

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BasePresenter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseSubscriber
import cryptomarket.eoinahern.ie.cryptomarket.domain.favourites.GetFavouritesInteractor
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.compareApiDeprecated
import javax.inject.Inject


@PerScreen
class FavouritesActivityPresenter @Inject constructor(private val getFavouritesInteractor: GetFavouritesInteractor) : BasePresenter<FavouritesActivityView>() {

	private var cryptoList: MutableList<CryptoCurrency> = mutableListOf()

	fun getFavourites() {

		getView()?.showLoading()

		getFavouritesInteractor.execute(object : BaseSubscriber<List<CryptoCurrency>>() {
			override fun onNext(t: List<CryptoCurrency>) {
				getView()?.hideLoading()
				initCryptoList(t)
			}

			override fun onError(e: Throwable) {
				getView()?.errorLoadingFavourites()
			}
		})
	}

	fun initCryptoList(cryptoList: List<CryptoCurrency>) {
		this.cryptoList.clear()
		this.cryptoList.addAll(cryptoList)
		getView()?.showFavourites()
	}

	fun getCrypto(position: Int): CryptoCurrency = cryptoList[position]

	fun initView(position: Int, favouriteRowView: FavouriteRowView) {
		val crypto = cryptoList[position]
		favouriteRowView.setFullName(crypto.CoinName)
		favouriteRowView.setIcon(compareApiDeprecated.plus(crypto.ImageUrl))
		favouriteRowView.setSymbol(crypto.Symbol)
	}

	fun getCount(): Int = cryptoList.size


	override fun detachView() {
		super.detachView()
		getFavouritesInteractor.disposeObs()
	}

}