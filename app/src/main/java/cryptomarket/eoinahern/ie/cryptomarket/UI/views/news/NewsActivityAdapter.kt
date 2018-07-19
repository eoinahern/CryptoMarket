package cryptomarket.eoinahern.ie.cryptomarket.UI.views.news

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.view.SimpleDraweeView
import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import kotlinx.android.synthetic.main.single_news_recycler_layout.view.*
import javax.inject.Inject


@PerScreen
class NewsActivityAdapter @Inject constructor() : RecyclerView.Adapter<NewsActivityAdapter.ViewHolder>() {

	private var newsList: MutableList<CryptoNewsItem> = mutableListOf()

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val item = newsList[position]
		holder.thumbnail.setImageURI(item.thumbnail)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsActivityAdapter.ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.single_news_recycler_layout, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int {
		return newsList.size
	}

	fun updateList(newsList: List<CryptoNewsItem>) {

		if (this.newsList.isNotEmpty())
			this.newsList.clear()

		this.newsList.addAll(newsList)
		notifyItemRangeChanged(0, newsList.size)
	}

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val thumbnail: SimpleDraweeView by lazy { view.findViewById<SimpleDraweeView>(R.id.newsThumbnail) }
	}


}