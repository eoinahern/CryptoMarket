package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.util.BottomItemDecoration
import cryptomarket.eoinahern.ie.cryptomarket.UI.util.LoadingView
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer.NavigationDrawerActivity
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyFullPriceDataDisplay
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : NavigationDrawerActivity(), MainActivityView {

	@Inject
	lateinit var presenter: MainActivityPresenter
	@Inject
	lateinit var adapter: MainActivityAdapter
	lateinit var llmanager: LinearLayoutManager
	private var offset: Int = 0
	private var limit: Int = 50

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setDrawerOnState()
		setUpRecycler()
		presenter.attachView(this)
		presenter.getCurrencyDataInitial()
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

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {

		menuInflater.inflate(R.menu.toolbar_currency_menu, menu)
		menu?.getItem(0)?.isChecked = true
		adapter.setCurrency(getString(R.string.euro_abv))
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {

		adapter.setCurrency(item?.title.toString())
		return super.onOptionsItemSelected(item)
	}

	private fun setUpRecycler() {

		llmanager = LinearLayoutManager(this)
		recycler.layoutManager = llmanager
		recycler.setHasFixedSize(false)
		recycler.addItemDecoration(BottomItemDecoration(this, R.color.dark_gray, 3f))
		recycler.addOnScrollListener(getOnScrollListener())
		recycler.adapter = adapter
	}

	override fun updateRecyclerView(dataList: List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>?>) {

		adapter.removeLoadingItems()
		adapter.updateCryptoData(dataList)
		offset += 50
	}

	private fun getOnScrollListener(): RecyclerView.OnScrollListener {

		return object : RecyclerView.OnScrollListener() {
			override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
				super.onScrolled(recyclerView, dx, dy)

				var visibleItemCount = llmanager.childCount
				var totalItemCount = llmanager.itemCount
				var firstVisibleItemPosition = llmanager.findFirstVisibleItemPosition()

				if (!adapter.isLoading() && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {

					adapter.showLoadingItems()
					presenter.getCurrencyUpdateData(offset, limit)
				}
			}
		}
	}

	override fun showError() {
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}

}
