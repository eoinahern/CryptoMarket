package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.View
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BaseActivity
import cryptomarket.eoinahern.ie.cryptomarket.UI.util.CONVERTED_TO
import cryptomarket.eoinahern.ie.cryptomarket.UI.util.CURRENCY_INFO
import cryptomarket.eoinahern.ie.cryptomarket.UI.util.LoadingView
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.HistoricalData
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : BaseActivity(), DetailsView {

	@Inject
	lateinit var presenter: DetailsActivityPresenter

	private val detailsToolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
	private val loadingView: LoadingView by lazy { findViewById<LoadingView>(R.id.loading_view) }
	private val lineGraph: LineChart by lazy { findViewById<LineChart>(R.id.line_graph) }

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		presenter.attachView(this)
		setUpToolbar()
		readIntent()
		setUpGraph()
		showLoading()

	}

	private fun setUpToolbar() {

		setSupportActionBar(detailsToolbar)
		supportActionBar?.apply {

			this.setDisplayHomeAsUpEnabled(true)
			this.setHomeButtonEnabled(true)
			this.setHomeAsUpIndicator(R.drawable.ic_back_dark)
		}

		detailsToolbar.setNavigationOnClickListener { _ -> finish() }
	}

	private fun readIntent() {

		val curr = intent.getParcelableExtra<CryptoCurrency>(CURRENCY_INFO)
		val convertedTo = intent.getStringExtra(CONVERTED_TO)
		supportActionBar?.title = curr.Symbol

		presenter.loadSingleCryptoData(curr.Symbol, convertedTo)
	}

	override fun inject() {
		(application as MyApp).getAppComponent()
				.plus(DetailsActivityComponent.DetailsActivityCompnentModule(this))
				.inject(this)
	}

	override fun getLayoutView(): Int {
		return R.layout.activity_details
	}

	companion object {
		fun getStartIntent(context: Context): Intent = Intent(context, DetailsActivity::class.java)
	}

	override fun showLoading() {
		loadingView.setState(LoadingView.State.LOADING)
		llayoutDetails.visibility = View.GONE
	}

	override fun hideLoading() {
		loadingView.hide()
	}

	override fun showError() {

	}

	override fun DisplayLoadedData() {

	}


	private fun setUpGraph() {

		lineGraph.setDrawBorders(false)
		lineGraph.xAxis.setDrawGridLines(false)
		lineGraph.axisRight.setDrawLabels(false)
		lineGraph.axisLeft.setDrawGridLines(false)
		lineGraph.setDrawGridBackground(false)
	}

	/**
	 * cant be done via xml unfortunately
	 *
	 */

	private fun addDataSetStyling(dataset: LineDataSet?) {

		dataset?.apply {
			color = ContextCompat.getColor(applicationContext, R.color.mint_green)
			lineWidth = 4f
			setDrawValues(false)
			setDrawCircles(false)
			mode = LineDataSet.Mode.CUBIC_BEZIER
		}
	}

	override fun displayGraphData(graphList: List<HistoricalData?>) {

		llayoutDetails.visibility = View.VISIBLE

		graphList.forEach {
			addDataSetStyling(it?.LineData)
		}

		lineGraph.data = LineData(graphList[0]?.LineData)
		lineGraph.invalidate()
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}

}
