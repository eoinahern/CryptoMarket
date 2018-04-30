package cryptomarket.eoinahern.ie.cryptomarket.UI.views.alerts


import android.os.Bundle
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BaseActivity

class AlertsActivity : BaseActivity(), AlertsView {


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun inject() {
		//inject stuff if required
	}

	override fun getLayoutView(): Int {
		return R.layout.activity_alerts

	}

	override fun showLoading() {

	}

	override fun hideLoading() {

	}
}
