package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import cryptomarket.eoinahern.ie.cryptomarket.UI.base.MvpView
import cryptomarket.eoinahern.ie.cryptomarket.data.models.HistoricalData

interface DetailsView : MvpView {

	fun showLoading()

	fun hideLoading()

	fun showError()

	fun DisplayLoadedData()

	fun DisplayGraphData(graphList : List<HistoricalData?>)

}