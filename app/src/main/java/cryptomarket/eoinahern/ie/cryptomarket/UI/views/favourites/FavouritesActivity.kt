package cryptomarket.eoinahern.ie.cryptomarket.UI.views.favourites

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer.NavigationDrawerActivity
import cryptomarket.eoinahern.ie.cryptomarket.tools.view.LoadingView
import kotlinx.android.synthetic.main.activity_favourites.*
import javax.inject.Inject

class FavouritesActivity : NavigationDrawerActivity(), FavouritesActivityView {

	@Inject
	lateinit var presenter: FavouritesActivityPresenter

	@Inject
	lateinit var adapter: FavouritesActivityAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setDrawerOnState()
		setUpRecycler()
	}

	override fun setDrawerOnState() {
		super.setDrawerOnState()
		favourites.isSelected = true
		favouritesTxt.typeface = Typeface.DEFAULT_BOLD
	}

	override fun inject() {
		(applicationContext as MyApp).getAppComponent()
				.plus(FavouritesActivityComponent.FavouritesActivityModule(this))
				.inject(this)
	}

	private fun setUpRecycler() {

	}

	companion object {
		fun getStartIntent(cont: Context): Intent {
			return Intent(cont, FavouritesActivity::class.java)
		}
	}

	override fun getLayoutView(): Int {
		return R.layout.activity_favourites
	}

	override fun showLoading() {
		loadingView.setState(LoadingView.State.LOADING)
	}

	override fun hideLoading() {
		loadingView.hide()
	}

	override fun showFavourites() {

	}

	override fun errorLoadingFavourites() {

	}
}
