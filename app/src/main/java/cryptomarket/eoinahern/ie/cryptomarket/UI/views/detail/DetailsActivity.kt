package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BaseActivity

class DetailsActivity : BaseActivity(), DetailsView {

	val detailsToolbar : Toolbar by lazy { findViewById<Toolbar>(R.id.detailsToolbar)}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setSupportActionBar(detailsToolbar)
		supportActionBar?.title = null
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_icon)
		detailsToolbar.setNavigationOnClickListener { _ ->  onBackPressed()}
	}

	override fun inject() {
		//not used yet
	}

	override fun getLayoutView() : Int = R.layout.activity_details

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
