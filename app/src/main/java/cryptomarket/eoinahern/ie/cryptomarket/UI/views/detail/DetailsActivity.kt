package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
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
	}

	override fun hideLoading() {
		loadingView.hide()
	}

	override fun showError() {

	}

	override fun DisplayLoadedData() {

	}

	override fun DisplayGraphData(graphList: List<HistoricalData?>) {

		var entries: MutableList<Entry> = mutableListOf()

		graphList[0]?.Data?.forEach {
			entries.add(Entry(it.time.toFloat(), it.close))
		}

		var dataSet = LineDataSet(entries, "data")
		dataSet.color = ContextCompat.getColor(this, R.color.mint_green)
		dataSet.lineWidth = 4f
		dataSet.setDrawValues(false)
		dataSet.setDrawCircles(false)
		dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
		lineGraph.axisLeft.setDrawGridLines(false)

		lineGraph.setDrawBorders(false)
		lineGraph.xAxis.setDrawGridLines(false)
		lineGraph.axisRight.setDrawLabels(false)
		lineGraph.setDrawGridBackground(false)
		lineGraph.data = LineData(dataSet)
		lineGraph.invalidate()

	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}

}
