package cryptomarket.eoinahern.ie.cryptomarket.UI.views.alerts

import cryptomarket.eoinahern.ie.cryptomarket.UI.base.MvpView


interface AlertsView : MvpView {

	fun showLoading()

	fun hideLoading()
}