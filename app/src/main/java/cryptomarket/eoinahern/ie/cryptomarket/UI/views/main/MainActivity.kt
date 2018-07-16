package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.jakewharton.rxbinding2.widget.RxSearchView
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail.DetailsActivity
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer.NavigationDrawerActivity
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CoinMarketCrypto
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyFullPriceDataDisplay
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.CONVERTED_TO
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.CURRENCY_FULL_PRICE
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
	lateinit var llmanager: LinearLayoutManager
	private lateinit var menuText: String

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setDrawerOnState()
		setUpRecycler()
		presenter.attachView(this)
		presenter.getCurrencyDataInitial()
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
		loadingView.setState(LoadingView.State.LOADING)
	}

	override fun hideLoading() {
		loadingView.hide()
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {

		menuInflater.inflate(R.menu.toolbar_currency_menu, menu)
		menu?.getItem(0)?.isChecked = true
		menuText = getString(R.string.euro_abv)
		adapter.setCurrency(menuText)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {

		menuText = item?.title.toString()
		adapter.setCurrency(menuText)
		return super.onOptionsItemSelected(item)
	}

	private fun setUpRecycler() {

		llmanager = LinearLayoutManager(this)
		recycler.layoutManager = llmanager
		recycler.setHasFixedSize(false)
		recycler.addItemDecoration(BottomItemDecoration(this, R.color.dark_gray, 3f))
		//recycler.addOnScrollListener(getOnScrollListener())
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
		adapter.setInitData(dataList)
	}

	override fun showNetworkError() {
		loadingView.setState(LoadingView.State.NETWORK_ERROR)
	}

	override fun showOtherError() {
		loadingView.setState(LoadingView.State.OTHER_ERROR)
	}

	override fun gotToDetail(crypto: CryptoCurrency?, fullPriceDataDisplay: CurrencyFullPriceDataDisplay?) {

		val intent = DetailsActivity.getStartIntent(this)

		//intent.putExtra(CURRENCY_INFO, crypto)
		//intent.putExtra(CONVERTED_TO, menuText)
		//intent.putExtra(CURRENCY_FULL_PRICE, fullPriceDataDisplay)
		//startActivity(intent)
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}

}
