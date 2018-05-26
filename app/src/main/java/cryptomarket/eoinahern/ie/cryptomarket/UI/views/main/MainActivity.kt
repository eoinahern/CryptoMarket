package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.util.LoadingView
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer.NavigationDrawerActivity
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyPriceConversions
import javax.inject.Inject

class MainActivity : NavigationDrawerActivity(), MainActivityView {

	@Inject
	lateinit var presenter: MainActivityPresenter
	@Inject
	lateinit var adapter: MainActivityAdapter
	lateinit var llmanager : LinearLayoutManager


	private val loadingView: LoadingView by lazy { findViewById<LoadingView>(R.id.loading_view) }
	private val recycler: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recycler) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setDrawerOnState()
		llmanager = LinearLayoutManager(this)
		presenter.attachView(this)
		presenter.getCurrencyData()
		showLoading()
	}

	override fun setDrawerOnState() {
		super.setDrawerOnState()
		main.isSelected = true
		mainTxt.typeface = Typeface.DEFAULT_BOLD
	}

	companion object {
		fun getStartIntent(cont: Context): Intent {
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
		loadingView.setState(LoadingView.State.LOADING)
	}

	override fun hideLoading() {
		loadingView.hide()
	}

	override fun updateRecyclerView(dataMap : HashMap<String, Pair<CryptoCurrency?, CurrencyPriceConversions?>>) {
		recycler.layoutManager = llmanager
		adapter.updateCryptoData(dataMap)
		recycler.adapter = adapter
	}

	override fun showError() {

	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}

}
