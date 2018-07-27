package cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BaseActivity
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.favourites.FavouritesActivity
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.main.MainActivity
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.news.NewsActivity

abstract class NavigationDrawerActivity : BaseActivity() {

	protected val main: View by lazy { findViewById<View>(R.id.drawerMain) }
	protected val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
	protected val drawerLayout: DrawerLayout by lazy { findViewById<DrawerLayout>(R.id.drawer_layout) }
	protected val favourites: View by lazy { findViewById<View>(R.id.drawerFavourites) }
	protected val news: View by lazy { findViewById<View>(R.id.drawerNews) }
	protected val mainTxt: TextView by lazy { findViewById<TextView>(R.id.main_txt) }
	protected val favouritesTxt: TextView by lazy { findViewById<TextView>(R.id.favourites_txt) }
	protected val newsTxt: TextView by lazy { findViewById<TextView>(R.id.news_txt) }

	private lateinit var drawerToggle: ActionBarDrawerToggle

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		supportActionBar?.title = null

		setUpDrawerToggle()
		setListeners()
	}

	private fun setUpDrawerToggle() {
		drawerToggle = object : ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer) {

			override fun onDrawerClosed(drawerView: View) {
				super.onDrawerClosed(drawerView)
				invalidateOptionsMenu()
			}

			override fun onDrawerOpened(drawerView: View) {
				super.onDrawerOpened(drawerView)
				invalidateOptionsMenu()
			}
		}

		drawerToggle.isDrawerIndicatorEnabled = false
		drawerToggle.setHomeAsUpIndicator(R.drawable.ic_hamburger_dark)

		toolbar.setNavigationOnClickListener {
			if (drawerLayout.isDrawerOpen(Gravity.START)) {
				drawerLayout.closeDrawers()
			} else {
				drawerLayout.openDrawer(Gravity.START)
			}
		}

		drawerLayout.addDrawerListener(drawerToggle)
	}

	private fun setListeners() {
		main.setOnClickListener {
			startActivity(MainActivity.getStartIntent(this))
			finish()
		}
		favourites.setOnClickListener {
			startActivity(FavouritesActivity.getStartIntent(this))
			finish()
		}
		news.setOnClickListener {
			startActivity(NewsActivity.getStartIntent(this))
			finish()
		}
	}

	override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
		super.onPostCreate(savedInstanceState, persistentState)
		drawerToggle.syncState()
	}

	override fun onConfigurationChanged(newConfig: Configuration?) {
		super.onConfigurationChanged(newConfig)
		drawerToggle.onConfigurationChanged(newConfig)
	}

	private fun cancelBoldText() {
		favourites.isSelected = false
		news.isSelected = false
		main.isSelected = false
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		if (drawerToggle.onOptionsItemSelected(item))
			return true

		return super.onOptionsItemSelected(item)
	}

	open fun setDrawerOnState() {
		cancelBoldText()
	}
}
