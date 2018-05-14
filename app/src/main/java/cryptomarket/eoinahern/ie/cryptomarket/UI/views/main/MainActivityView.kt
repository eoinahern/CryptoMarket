package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import cryptomarket.eoinahern.ie.cryptomarket.UI.base.MvpView


interface MainActivityView : MvpView {

	fun showLoading()

	fun hideLoading()

	fun updateRecyclerView(data : String)

	fun showError()


}