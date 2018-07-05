package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.View
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
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

	private var graphListCopy: MutableList<HistoricalData?> = mutableListOf()

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		presenter.attachView(this)
		setUpToolbar()
		readIntent()
		setUpGraph()
		showLoading()
		initGraphButtons()
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

	/**
	 * need to set up programmatically.
	 *
	 */

	private fun setUpGraph() {

		lineGraph.setDrawBorders(false)
		lineGraph.xAxis.setDrawGridLines(false)
		lineGraph.setDrawBorders(false)
		lineGraph.xAxis.setDrawLabels(false)
		lineGraph.xAxis.position = XAxis.XAxisPosition.BOTTOM
		lineGraph.axisRight.setDrawLabels(false)
		lineGraph.axisLeft.setDrawGridLines(false)
		lineGraph.axisRight.setDrawGridLines(false)
		lineGraph.setDrawGridBackground(false)
		lineGraph.axisRight.isEnabled = false
		lineGraph.axisLeft.setDrawAxisLine(true)
		lineGraph.axisLeft.textColor = ContextCompat.getColor(applicationContext, R.color.colorAccent)
		lineGraph.legend.isEnabled = false
		lineGraph.description = null
	}

	@SuppressLint("ResourceType")
	private fun addDataSetStyling(dataset: LineDataSet?) {

		dataset?.apply {
			color = ContextCompat.getColor(applicationContext, R.color.mint_green_heavy)
			lineWidth = 4f
			setDrawValues(false)
			setDrawCircles(false)
			mode = LineDataSet.Mode.CUBIC_BEZIER
		}
	}

	override fun initGraphData(graphList: MutableList<HistoricalData?>) {

		llayoutDetails.visibility = View.VISIBLE
		graphListCopy.addAll(graphList)
		graphList.clear()

		graphListCopy.forEach {
			addDataSetStyling(it?.LineData)
		}

		lineGraph.data = LineData(graphListCopy[0]?.LineData)
		lineGraph.invalidate()
	}

	private fun initGraphButtons() {

		twelveHourBtn.setOnClickListener { updateGraph(0) }
		twoFourHourBtn.setOnClickListener { updateGraph(1) }
		oneMonthBtn.setOnClickListener { updateGraph(2) }
		sixMonthsBtn.setOnClickListener { updateGraph(3) }
		oneYearBtn.setOnClickListener { updateGraph(4) }
	}

	private fun updateGraph(index: Int) {

		lineGraph.data = LineData(graphListCopy[index]?.LineData)
		lineGraph.invalidate()
	}


	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}

}
