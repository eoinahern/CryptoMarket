package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import cryptomarket.eoinahern.ie.cryptomarket.UI.base.MvpView

interface DetailsView : MvpView {

	fun showLoading()

	fun hideLoading()

	fun showError()

	fun DisplayLoadedData()

}