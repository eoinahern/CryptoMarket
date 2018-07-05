package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import com.github.mikephil.charting.data.LineDataSet
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.MvpView
import cryptomarket.eoinahern.ie.cryptomarket.data.models.HistoricalData

interface DetailsView : MvpView {

	fun showLoading()

	fun hideLoading()

	fun showError()

	fun initGraphData(graphList: MutableList<HistoricalData?>)

}