package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BaseActivity

class DetailsActivity : BaseActivity(), DetailsView {

	val detailsToolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.detailsToolbar) }
	var actionBar: ActionBar? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_details)

		this.setSupportActionBar(detailsToolbar)

		actionBar = supportActionBar?.apply {

			this.title = "BTC"
			this.setDisplayHomeAsUpEnabled(true)
			this.setHomeButtonEnabled(true)
			this.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
		}
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {

		return when (item.itemId) {
			android.R.id.home -> {
				this.finish()
				true
			}
			else -> super.onOptionsItemSelected(item)
		}
	}

	override fun inject() {
		//not used yet
	}

	override fun getLayoutView(): Int = R.layout.activity_details

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
