package cryptomarket.eoinahern.ie.cryptomarket.UI.views.news

import cryptomarket.eoinahern.ie.cryptomarket.UI.base.MvpView


interface NewsView : MvpView {

	fun showLoading()

	fun hideLoading()

	fun getNews()
}