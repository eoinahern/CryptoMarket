package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.jakewharton.rxbinding2.widget.RxSearchView
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail.DetailsActivity
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer.NavigationDrawerActivity
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CoinMarketCrypto
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.CONVERTED_TO
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.CURRENCY_INFO
import cryptomarket.eoinahern.ie.cryptomarket.tools.decoration.BottomItemDecoration
import cryptomarket.eoinahern.ie.cryptomarket.tools.view.LoadingView
import io.reactivex.android.schedulers.AndroidSchedulers

import kotlinx.android.synthetic.main.activity_main.*

import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : NavigationDrawerActivity(), MainActivityView {

	@Inject
	lateinit var presenter: MainActivityPresenter
	@Inject
	lateinit var adapter: MainActivityAdapter
	private lateinit var llmanager: LinearLayoutManager
	private lateinit var menuText: String

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setDrawerOnState()
		setUpRecycler()
		menuText = getString(R.string.usd_abv)
		presenter.attachView(this)
		presenter.getCurrencyUpdateData(menuText)
		showLoading()
		cryptoSearchView.isEnabled = false
		setUpSearchListener()
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
		loadingView.visibility = View.VISIBLE
		recycler.visibility = View.GONE
		loadingView.setState(LoadingView.State.LOADING)
	}

	override fun hideLoading() {
		recycler.visibility = View.VISIBLE
		loadingView.hide()
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.toolbar_currency_menu, menu)
		menu?.getItem(0)?.isChecked = true
		adapter.setCurrency(menuText)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		menuText = item?.title.toString()
		item?.isChecked = true
		adapter.setCurrency(menuText)
		adapter.clear()
		showLoading()
		presenter.getCurrencyUpdateData(menuText)
		return super.onOptionsItemSelected(item)
	}

	private fun setUpRecycler() {
		llmanager = LinearLayoutManager(this)
		recycler.layoutManager = llmanager
		recycler.setHasFixedSize(false)
		recycler.addItemDecoration(BottomItemDecoration(this, R.color.dark_gray, 3f))
		recycler.adapter = adapter
		cryptoSearchView.isEnabled = true
	}

	private fun setUpSearchListener() {
		RxSearchView.queryTextChanges(cryptoSearchView)
				.debounce(500, TimeUnit.MILLISECONDS)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe({ charSequence ->
					adapter.filter.filter(charSequence)
				}, {
					it.printStackTrace()
				})
	}

	override fun updateRecyclerView(dataList: List<CoinMarketCrypto>) {
		adapter.updateCryptoData(dataList)
	}

	override fun showNetworkError() {
		loadingView.setState(LoadingView.State.NETWORK_ERROR)
	}

	override fun showOtherError() {
		loadingView.setState(LoadingView.State.OTHER_ERROR)
	}

	override fun gotToDetail(symbol: String) {
		val intent = DetailsActivity.getStartIntent(this)
		intent.putExtra(CURRENCY_INFO, presenter.getCurrencyMapItem(symbol))
		intent.putExtra(CONVERTED_TO, menuText)
		startActivity(intent)
	}

	override fun onPause() {
		super.onPause()
		presenter.persistFavourites()
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}
}
