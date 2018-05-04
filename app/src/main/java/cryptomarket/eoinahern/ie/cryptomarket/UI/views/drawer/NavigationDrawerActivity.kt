package cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BaseActivity
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.alerts.AlertsActivity
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.main.MainActivity

open abstract class NavigationDrawerActivity :BaseActivity() {

	private val main: View by lazy { findViewById<View>(R.id.drawer_main) }
	protected val toolbar : Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar)}
	private val drawerLayout : DrawerLayout by lazy { findViewById<DrawerLayout>(R.id.drawer_layout)}
	protected val all: View by lazy { findViewById<View>(R.id.drawer_all_crypto) }
	protected val alerts: View by lazy { findViewById<View>(R.id.drawer_alerts) }

	private lateinit var drawerToggle : ActionBarDrawerToggle

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)


        setSupportActionBar(toolbar)
		setUpDrawerToggle()
		setListeners()
	}

	private fun setUpDrawerToggle() {

		drawerToggle = object : ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer) {


			override fun onDrawerClosed(drawerView: View) {
				super.onDrawerClosed(drawerView)
			}

			override fun onDrawerOpened(drawerView: View) {
				super.onDrawerOpened(drawerView)
			}
		}

		drawerToggle.isDrawerIndicatorEnabled = false
		drawerToggle.setHomeAsUpIndicator(R.drawable.ic_hamburger_dark)
		drawerToggle.setToolbarNavigationClickListener { drawerLayout.openDrawer(Gravity.LEFT) }

		drawerLayout.addDrawerListener(drawerToggle)


	}

	private fun setListeners() {

		main.setOnClickListener { startActivity(MainActivity.getStartIntent(this)) }
		all.setOnClickListener { startActivity(MainActivity.getStartIntent(this)) }
		alerts.setOnClickListener { startActivity(AlertsActivity.getStartIntent(this)) }
	}
}
