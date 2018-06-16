package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BaseActivity

class DetailsActivity : BaseActivity(), DetailsView {

	private val detailsToolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
	private var actionBar: ActionBar? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		this.setSupportActionBar(detailsToolbar)
		actionBar = supportActionBar
		actionBar?.apply {

			this.title = "BTC"
			this.setDisplayHomeAsUpEnabled(true)
			this.setHomeButtonEnabled(true)
			this.setHomeAsUpIndicator(R.drawable.ic_back_dark)
		}

		detailsToolbar.setNavigationOnClickListener { _ -> finish() }
	}

	override fun inject() {
		//not used yet
	}

	override fun getLayoutView(): Int {
		return R.layout.activity_details
	}

	companion object {
		fun getStartIntent(context: Context): Intent = Intent(context, DetailsActivity::class.java)
	}

	override fun showLoading() {

	}

	override fun hideLoading() {

	}

	override fun showError() {

	}

	override fun DisplayLoadedData() {

	}

}
