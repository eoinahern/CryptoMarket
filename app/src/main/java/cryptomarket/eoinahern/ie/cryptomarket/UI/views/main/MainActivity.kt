package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer.NavigationDrawerActivity
import javax.inject.Inject

class MainActivity : NavigationDrawerActivity(), MainActivityView{


	@Inject lateinit var presenter : MainActivityPresenter
	@Inject lateinit  var adapter : MainActivityAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setDrawerOnState()
	}

	override fun setDrawerOnState() {
		super.setDrawerOnState()
		main.isSelected = true
		mainTxt.typeface = Typeface.DEFAULT_BOLD
	}

	companion object {
		fun getStartIntent(cont : Context) : Intent {
			return Intent(cont, MainActivity::class.java)
		}
	}

	override fun inject() {
		(applicationContext as MyApp).getAppComponent().plus(MainActivityComponent.MainActivityModule(this)).inject(this)
	}

	override fun getLayoutView(): Int {
		return R.layout.activity_main
	}

	override fun showLoading() {

	}

	override fun hideLoading() {

	}

	override fun updateRecyclerView() {

	}

	override fun showError() {

	}

}
