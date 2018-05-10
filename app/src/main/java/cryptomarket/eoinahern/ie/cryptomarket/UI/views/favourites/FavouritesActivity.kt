package cryptomarket.eoinahern.ie.cryptomarket.UI.views.favourites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer.NavigationDrawerActivity
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.main.MainActivity

class FavouritesActivity : NavigationDrawerActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
        favourites.isSelected = true
	}

	override fun inject() {

	}

	companion object {
		fun getStartIntent(cont : Context) : Intent {
			return Intent(cont, FavouritesActivity::class.java)
		}
	}

	override fun getLayoutView(): Int {
		return R.layout.activity_favourites
	}
}
