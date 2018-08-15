package cryptomarket.eoinahern.ie.cryptomarket.UI.views.favourites

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail.DetailsActivity
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.drawer.NavigationDrawerActivity
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.CONVERTED_TO
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.CURRENCY_INFO
import cryptomarket.eoinahern.ie.cryptomarket.tools.decoration.BottomItemDecoration
import cryptomarket.eoinahern.ie.cryptomarket.tools.view.LoadingView
import kotlinx.android.synthetic.main.activity_favourites.*
import javax.inject.Inject

class FavouritesActivity : NavigationDrawerActivity(), FavouritesActivityView, ItemSelectCallback {

	@Inject
	lateinit var presenter: FavouritesActivityPresenter

	@Inject
	lateinit var adapter: FavouritesActivityAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setDrawerOnState()
		setTitle()
		setUpRecycler()
		presenter.attachView(this)
		presenter.getFavourites()
	}

	override fun setDrawerOnState() {
		super.setDrawerOnState()
		favourites.isSelected = true
		favouritesTxt.typeface = Typeface.DEFAULT_BOLD
	}

	private fun setTitle() {
		supportActionBar?.title = getString(R.string.favourites)
	}

	override fun inject() {
		(applicationContext as MyApp).getAppComponent()
				.plus(FavouritesActivityComponent.FavouritesActivityModule(this))
				.inject(this)
	}

	private fun setUpRecycler() {
		recycler.layoutManager = LinearLayoutManager(this)
		recycler.addItemDecoration(BottomItemDecoration(this, R.color.dark_gray, 3f))
		adapter.setCallback(this)
		recycler.adapter = adapter
	}

	companion object {
		fun getStartIntent(cont: Context): Intent {
			return Intent(cont, FavouritesActivity::class.java)
		}
	}

	override fun getLayoutView(): Int {
		return R.layout.activity_favourites
	}

	override fun showLoading() {
		loadingView.setState(LoadingView.State.LOADING)
	}

	override fun hideLoading() {
		loadingView.hide()
	}

	override fun showFavourites() {
		adapter.notifyDataSetChanged()
	}

	override fun errorLoadingFavourites() {
		loadingView.setState(LoadingView.State.NO_DATA)
	}

	override fun showEmpty() {
		loadingView.visibility = View.VISIBLE
		loadingView.setState(LoadingView.State.NO_DATA)
	}

	override fun selectFavourite(position: Int) {
		val intent = DetailsActivity.getStartIntent(this)
		intent.putExtra(CURRENCY_INFO, presenter.getCrypto(position))
		intent.putExtra(CONVERTED_TO, presenter.getConvertToString())
		startActivity(intent)
	}

	override fun deleteFavourite(position: Int) {
		showConfirmDialog(position)
	}

	private fun showConfirmDialog(position: Int) {
		MaterialDialog.Builder(this)
				.title(R.string.delete_title)
				.content(R.string.delete_content)
				.positiveText(R.string.agree)
				.negativeText(R.string.cancel)
				.onNegative { dialog, _ ->
					dialog.dismiss()
				}
				.onPositive { _, _ ->
					presenter.deleteFromFavourites(position)
				}
				.show()
	}

	override fun onDeleteComplete(position: Int) {
		adapter.removeItem(position)
	}

	override fun onDeleteFailed() {
		Toast.makeText(this, R.string.delete_failed, Toast.LENGTH_LONG).show()
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}
}
