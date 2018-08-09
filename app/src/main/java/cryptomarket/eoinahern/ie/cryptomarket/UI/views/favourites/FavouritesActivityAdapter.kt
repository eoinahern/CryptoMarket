package cryptomarket.eoinahern.ie.cryptomarket.UI.views.favourites

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import javax.inject.Inject

@PerScreen
class FavouritesActivityAdapter @Inject constructor(private val presenter: FavouritesActivityPresenter) : RecyclerView.Adapter<FavouritesActivityAdapter.ViewHolder>() {




	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesActivityAdapter.ViewHolder {
		//TODO
	}

	override fun getItemCount(): Int {

	}

	override fun onBindViewHolder(holder: FavouritesActivityAdapter.ViewHolder, position: Int) {

	}


	class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

	}


}