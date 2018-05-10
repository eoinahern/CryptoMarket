package cryptomarket.eoinahern.ie.cryptomarket.UI.views.currency

import android.os.Bundle
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer.NavigationDrawerActivity

class CurrencyActivity : NavigationDrawerActivity() {


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

	}

	override fun inject() {
	}

	override fun getLayoutView(): Int {
		return R.layout.activity_currency
	}
}
