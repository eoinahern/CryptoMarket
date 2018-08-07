package cryptomarket.eoinahern.ie.cryptomarket.UI.views.favourites

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer.NavigationDrawerActivity

class FavouritesActivity : NavigationDrawerActivity() {


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setDrawerOnState()
	}

	 override fun setDrawerOnState() {
		 super.setDrawerOnState()
		 favourites.isSelected = true
		 favouritesTxt.typeface = Typeface.DEFAULT_BOLD
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
