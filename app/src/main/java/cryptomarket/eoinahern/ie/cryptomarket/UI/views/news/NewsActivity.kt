package cryptomarket.eoinahern.ie.cryptomarket.UI.views.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer.NavigationDrawerActivity
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import cryptomarket.eoinahern.ie.cryptomarket.tools.view.LoadingView
import kotlinx.android.synthetic.main.activity_news.*
import javax.inject.Inject

class NewsActivity : NavigationDrawerActivity(), NewsView {

	@Inject
	lateinit var presenter: NewsActivityPresenter

	@Inject
	lateinit var adapter: NewsActivityAdapter

	private val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(this) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		presenter.attachView(this)
		showLoading()
		setUpRecyclerView()
		getNews()
	}

	override fun getLayoutView(): Int = R.layout.activity_news

	override fun inject() {
		(application as MyApp).getAppComponent().plus(NewsActivityComponent.NewsActivityModule(this))
				.inject(this)
	}

	companion object {
		fun getStartIntent(context: Context) = Intent(context, NewsActivity::class.java)
	}

	private fun setUpRecyclerView() {
		recyclerView.layoutManager = layoutManager
		recyclerView.adapter = adapter
	}

	override fun showLoading() {
		loadingView.setState(LoadingView.State.LOADING)
	}

	override fun hideLoading() {
		loadingView.hide()
	}

	override fun getNews() {
		presenter.getNews()
	}

	override fun updateNews(cryptoNewsList: List<CryptoNewsItem>) {
		adapter.updateList(cryptoNewsList)
	}

	override fun showConnectionError() {
		loadingView.setState(LoadingView.State.NETWORK_ERROR)
	}

	override fun showOtherError() {
		loadingView.setState(LoadingView.State.OTHER_ERROR)
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}
}
