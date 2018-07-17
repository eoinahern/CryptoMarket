package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Xml
import android.view.MotionEvent
import android.view.View
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.ChartTouchListener
import com.github.mikephil.charting.listener.OnChartGestureListener
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.R.id.*
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BaseActivity
import cryptomarket.eoinahern.ie.cryptomarket.data.models.*
import cryptomarket.eoinahern.ie.cryptomarket.tools.view.LoadingView
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.*
import cryptomarket.eoinahern.ie.cryptomarket.tools.date.DateUtil
import kotlinx.android.synthetic.main.activity_details.*
import java.net.URLDecoder
import javax.inject.Inject

/**
 * could refactor this to remove some of the string
 * manipulation done in the view. using a mapper class.
 * graph stuff and listners are directly related to view.
 * those need to be manipulated in view. doesnt make sense
 * to pass lineGraph obj (view) to presenter.
 */

class DetailsActivity : BaseActivity(), DetailsView, OnChartGestureListener, OnChartValueSelectedListener {

	@Inject
	lateinit var presenter: DetailsActivityPresenter

	@Inject
	lateinit var dateUtil: DateUtil

	private val detailsToolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
	private val loadingView: LoadingView by lazy { findViewById<LoadingView>(R.id.loading_view) }
	private val lineGraph: LineChart by lazy { findViewById<LineChart>(R.id.line_graph) }

	private var graphListCopy: MutableList<HistoricalData?> = mutableListOf()
	private lateinit var toCurrency: String
	private lateinit var toCurrencySymbol: String

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		presenter.attachView(this)
		setUpToolbar()
		readIntent()
		setUpGraph()
		showLoading()
		initGraphButtons()
		initLinkSelect()
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
		toCurrency = intent.getStringExtra(CONVERTED_TO)
		setCryptoCurrencyToViews(curr)
		presenter.getCoinInfo(curr.Id, curr.Symbol, toCurrency)
	}

	private fun setCryptoCurrencyToViews(cryptoCurrency: CryptoCurrency) {
		actionBarIcon.setImageURI(compareApiDeprecated.plus(cryptoCurrency.ImageUrl))
		toolbarCryptoTxt.text = cryptoCurrency.FullName
		symbolTxt.text = cryptoCurrency.Symbol
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

	override fun showNetworkError() {
		loadingView.setState(LoadingView.State.NETWORK_ERROR)
	}

	override fun showOtherError() {
		loadingView.setState(LoadingView.State.OTHER_ERROR)
	}

	/**
	 * needs to set up programmatically.
	 *
	 */

	private fun setUpGraph() {
		lineGraph.setDrawBorders(false)
		lineGraph.onChartGestureListener = this
		lineGraph.setOnChartValueSelectedListener(this)
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
	private fun addDataSetStyling(dataSet: LineDataSet?) {

		dataSet?.apply {
			color = ContextCompat.getColor(applicationContext, R.color.mint_green_heavy)
			lineWidth = GRAPH_LINE_WIDTH
			setDrawValues(false)
			setDrawCircles(false)
			mode = LineDataSet.Mode.CUBIC_BEZIER
		}
	}

	override fun initGraphData(graphList: MutableList<HistoricalData?>) {

		graphListCopy.addAll(graphList)
		graphList.clear()

		graphListCopy.forEach {
			addDataSetStyling(it?.LineData)
		}

		lineGraph.data = LineData(graphListCopy[0]?.LineData)
		setLastDataEntryOnTextViews()
		lineGraph.invalidate()
		llayoutDetails.visibility = View.VISIBLE
	}

	private fun initGraphButtons() {
		twelveHourBtn.setOnClickListener { updateGraph(0) }
		twoFourHourBtn.setOnClickListener { updateGraph(1) }
		oneMonthBtn.setOnClickListener { updateGraph(2) }
		sixMonthsBtn.setOnClickListener { updateGraph(3) }
		oneYearBtn.setOnClickListener { updateGraph(4) }
	}

	private fun initLinkSelect() {
		whitepaperTxt.movementMethod = LinkMovementMethod.getInstance()
		blurbTxt.movementMethod = LinkMovementMethod.getInstance()
	}

	private fun updateGraph(index: Int) {
		val lineData = LineData(graphListCopy[index]?.LineData)
		(lineData.dataSets[0] as LineDataSet).setDrawHighlightIndicators(false)
		lineGraph.data = lineData
		lineGraph.invalidate()
	}

	override fun showGeneralCoinInfo(snapShotData: SnapShotData) {
		val general = snapShotData.General
		val ico = snapShotData.ico

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			blurbTxt.text = Html.fromHtml(general.description, Html.FROM_HTML_MODE_LEGACY)
			whitepaperTxt.text = Html.fromHtml(URLDecoder.decode(ico.whitePaper, "UTF-8"), Html.FROM_HTML_MODE_LEGACY)
		} else {
			blurbTxt.text = Html.fromHtml(general.description)
			whitepaperTxt.text = Html.fromHtml(ico.whitePaper)
		}

		twitterUrlTxt.text = general.getTwitterFullLink()
		websiteTxt.text = general.websiteUrl
		blogTxt.text = ico.getBlogLink()
	}

	override fun showFullPriceData(currencyFullPrice: CurrencyFullPriceDataDisplay?) {

		toCurrencySymbol = currencyFullPrice?.TOSYMBOL ?: ""
		marketcapTxt.text = currencyFullPrice?.MKTCAP
		marketTxt.text = currencyFullPrice?.MARKET
		supplyTxt.text = currencyFullPrice?.SUPPLY
		vol24hTxt.text = currencyFullPrice?.VOLUME24HOURTO
		totalVol24hTxt.text = currencyFullPrice?.TOTALVOLUME24HTO
		lowDayTxt.text = currencyFullPrice?.LOWDAY
		highDayTxt.text = currencyFullPrice?.HIGHDAY
	}

	private fun getLineDataSetFromGraph() = lineGraph.lineData.dataSets[0] as LineDataSet

	/**
	 * sets price and date on top textviews from last Entry in Data set.
	 */

	private fun setLastDataEntryOnTextViews() {
		val dataSet = getLineDataSetFromGraph()
		dataSet.setDrawHighlightIndicators(false)
		val e: Entry = dataSet.getEntryForIndex(dataSet.entryCount - 1)
		setPriceAndDate(e.y.toString(), e.x)
	}

	/**
	 * series of listener methods to determine chart crosshairs location etc.
	 */

	override fun onChartGestureEnd(me: MotionEvent?, lastPerformedGesture: ChartTouchListener.ChartGesture?) {
		setLastDataEntryOnTextViews()
	}

	override fun onChartFling(me1: MotionEvent?, me2: MotionEvent?, velocityX: Float, velocityY: Float) {
	}

	override fun onChartSingleTapped(me: MotionEvent?) {
	}

	override fun onChartGestureStart(me: MotionEvent?, lastPerformedGesture: ChartTouchListener.ChartGesture?) {
	}

	override fun onChartScale(me: MotionEvent?, scaleX: Float, scaleY: Float) {
	}

	override fun onChartLongPressed(me: MotionEvent?) {
	}

	override fun onChartDoubleTapped(me: MotionEvent?) {
	}

	override fun onChartTranslate(me: MotionEvent?, dX: Float, dY: Float) {
	}

	override fun onNothingSelected() {
	}

	override fun onValueSelected(e: Entry, h: Highlight) {
		val dataSet = getLineDataSetFromGraph()
		dataSet.setDrawHighlightIndicators(true)
		setPriceAndDate(e.y.toString(), e.x)
	}

	private fun setPriceAndDate(price: String, date: Float) {
		valueTxt.text = toCurrencySymbol.plus(price)
		dateTxt.text = dateUtil.getLocalDateTime(date.toLong())
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}
}
