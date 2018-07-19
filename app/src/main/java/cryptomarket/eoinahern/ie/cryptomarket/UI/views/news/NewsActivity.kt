package cryptomarket.eoinahern.ie.cryptomarket.UI.views.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer.NavigationDrawerActivity
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import javax.inject.Inject

class NewsActivity : NavigationDrawerActivity(), NewsView {

	/*Inject
	private lateinit var presenter: NewsActivityPresenter*/

	/*@Inject
	private lateinit var adapter: NewsActivityAdapter*/

	private val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(this) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setUpAdapter()
	}

	override fun getLayoutView(): Int = R.layout.activity_news

	override fun inject() {
	}

	companion object {
		fun getStartIntent(context: Context) = Intent(context, NewsActivity::class.java)
	}

	fun setUpAdapter() {

	}

	override fun showLoading() {

	}

	override fun hideLoading() {

	}

	override fun getNews() {
	}

	override fun updateNews(cryptoNewsList: List<CryptoNewsItem>) {

	}

	override fun showConnectionError() {

	}

	override fun showOtherError() {

	}
}
