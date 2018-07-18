package cryptomarket.eoinahern.ie.cryptomarket.UI.views.news

import android.os.Bundle
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BaseActivity

class NewsActivity : BaseActivity(), NewsView {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun getLayoutView(): Int = R.layout.activity_news

	override fun inject() {
	}

	override fun showLoading() {

	}

	override fun hideLoading() {

	}

	override fun getNews() {

	}
}
