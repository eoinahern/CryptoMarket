package cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer

import android.os.Bundle
import android.view.View
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BaseActivity
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.alerts.AlertsActivity
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.main.MainActivity

open abstract class NavigationDrawerActivity : BaseActivity() {

	protected val main: View by lazy { findViewById<View>(R.id.drawer_main) }
	protected val all: View by lazy { findViewById<View>(R.id.drawer_all_crypto) }
	protected val alerts: View by lazy { findViewById<View>(R.id.drawer_alerts) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setListeners()
	}

	private fun setListeners() {

		main.setOnClickListener { startActivity(MainActivity.getStartIntent(this)) }
		all.setOnClickListener { startActivity(MainActivity.getStartIntent(this)) }
		alerts.setOnClickListener { startActivity(AlertsActivity.getStartIntent(this)) }
	}
}
