package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import cryptomarket.eoinahern.ie.cryptomarket.UI.base.MvpView
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyFullPriceDataDisplay
import cryptomarket.eoinahern.ie.cryptomarket.data.models.HistoricalData
import cryptomarket.eoinahern.ie.cryptomarket.data.models.SnapShotData

interface DetailsView : MvpView {

	fun showLoading()

	fun hideLoading()

	fun showNetworkError()

	fun showOtherError()

	fun initGraphData(graphList: MutableList<HistoricalData?>)

	fun showGeneralCoinInfo(snapShotData: SnapShotData)

	fun showFullPriceData(currencyFullPriceData: CurrencyFullPriceDataDisplay?)

}