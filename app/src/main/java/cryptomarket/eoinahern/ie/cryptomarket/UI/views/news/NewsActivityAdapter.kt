package cryptomarket.eoinahern.ie.cryptomarket.UI.views.news

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

class NewsActivityAdapter : RecyclerView.Adapter<NewsActivityAdapter.ViewHolder>() {

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