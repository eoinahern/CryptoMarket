package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BaseActivity
import cryptomarket.eoinahern.ie.cryptomarket.UI.util.CURRENCY_INFO
import cryptomarket.eoinahern.ie.cryptomarket.UI.util.LoadingView
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import javax.inject.Inject

class DetailsActivity : BaseActivity(), DetailsView {

	@Inject lateinit var presenter : DetailsActivityPresenter

	private val detailsToolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
	private val loadingView: LoadingView by lazy { findViewById<LoadingView>(R.id.loading_view) }

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
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
		supportActionBar?.title = curr.Symbol
	}

	override fun inject() {
		(application  as MyApp).getAppComponent()
				.plus(DetailsActivityComponent.DetailsActivityCompnentModule(this)).inject(this)
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

}
