package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer.NavigationDrawerActivity

class MainActivity : NavigationDrawerActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		main.isSelected = true
	}

	companion object {
		fun getStartIntent(cont : Context) : Intent {
			return Intent(cont, MainActivity::class.java)
		}
	}

	override fun inject() {
	}

	override fun getLayoutView(): Int {
		return R.layout.activity_main
	}
}
