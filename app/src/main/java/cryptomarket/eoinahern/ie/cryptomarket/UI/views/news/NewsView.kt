package cryptomarket.eoinahern.ie.cryptomarket.UI.views.news

import cryptomarket.eoinahern.ie.cryptomarket.UI.base.MvpView
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem


interface NewsView : MvpView {

	fun showLoading()

	fun hideLoading()

	fun getNews()

	fun updateNews(cryptoNewsList: List<CryptoNewsItem>)

	fun showConnectionError()

	fun showOtherError()
}