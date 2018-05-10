package cryptomarket.eoinahern.ie.cryptomarket.UI.views.alerts


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BaseActivity
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer.NavigationDrawerActivity
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.main.MainActivity
import javax.inject.Inject

class AlertsActivity : NavigationDrawerActivity(), AlertsView {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		alerts.isSelected = true
	}

	override fun inject() {
		//inject stuff if

		(applicationContext as MyApp).getAppComponent().plus(AlertsActivityComponent.AlertsActivityModule(this)).inject(this)
	}

	override fun getLayoutView(): Int {
		return R.layout.activity_alerts
	}


	companion object {
		fun getStartIntent(cont : Context) : Intent {
			return Intent(cont, AlertsActivity::class.java)
		}
	}

	override fun showLoading() {

	}

	override fun hideLoading() {

	}
}
