package cryptomarket.eoinahern.ie.cryptomarket.UI.views.news

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import javax.inject.Inject


@PerScreen
class NewsActivityAdapter @Inject constructor() : RecyclerView.Adapter<NewsActivityAdapter.ViewHolder>() {

	private var newsList: List<CryptoNewsItem> = mutableListOf()

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsActivityAdapter.ViewHolder {
		return ViewHolder(View(parent.context))
	}

	override fun getItemCount(): Int {
		return 1
	}

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

	}


}