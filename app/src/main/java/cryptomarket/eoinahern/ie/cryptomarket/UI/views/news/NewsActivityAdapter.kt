package cryptomarket.eoinahern.ie.cryptomarket.UI.views.news

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import cryptomarket.eoinahern.ie.cryptomarket.tools.date.DateUtil
import javax.inject.Inject


@PerScreen
class NewsActivityAdapter @Inject constructor(private val dateUtil: DateUtil,
											  private val presenter: NewsActivityPresenter) : RecyclerView.Adapter<NewsActivityAdapter.ViewHolder>() {

	private var newsList: MutableList<CryptoNewsItem> = mutableListOf()

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val item = newsList[position]
		holder.thumbnail.setImageURI(item.thumbnail)
		holder.article.text = item.description
		holder.date.text = dateUtil.getLocalDateFromString(item.publishedAt)
		holder.title.text = item.title
		holder.category.text = item.primaryCategory

		holder.itemView.setOnClickListener { presenter.navigateToLink(item.url) }
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsActivityAdapter.ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.single_news_recycler_layout, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount() = newsList.size

	fun updateList(newsList: List<CryptoNewsItem>) {

		if (this.newsList.isNotEmpty())
			this.newsList.clear()

		this.newsList.addAll(newsList)
		notifyItemRangeChanged(0, newsList.size)
	}

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val thumbnail: SimpleDraweeView by lazy { view.findViewById<SimpleDraweeView>(R.id.newsThumbnail) }
		val title: TextView by lazy { view.findViewById<TextView>(R.id.titleText) }
		val date: TextView by lazy { view.findViewById<TextView>(R.id.dateText) }
		val article: TextView by lazy { view.findViewById<TextView>(R.id.articleTxt) }
		val category: TextView by lazy { view.findViewById<TextView>(R.id.categoryTxt) }

	}


}