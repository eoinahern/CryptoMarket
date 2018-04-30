package cryptomarket.eoinahern.ie.cryptomarket.UI.views.alerts


import android.content.SharedPreferences
import android.os.Bundle
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BaseActivity
import javax.inject.Inject

class AlertsActivity : BaseActivity(), AlertsView {

	@Inject lateinit var  sharedPrefs : SharedPreferences

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		println(sharedPrefs.getBoolean("blah", false))
	}

	override fun inject() {
		//inject stuff if

		(applicationContext as MyApp).getAppComponent().plus(AlertsActivityComponent.AlertsActivityModule(this)).inject(this)
	}

	override fun getLayoutView(): Int {
		return R.layout.activity_alerts

	}

	override fun showLoading() {

	}

	override fun hideLoading() {

	}
}
