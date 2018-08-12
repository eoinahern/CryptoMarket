package cryptomarket.eoinahern.ie.cryptomarket.UI.views.favourites

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.R
import javax.inject.Inject

@PerScreen
class FavouritesActivityAdapter @Inject constructor(private val presenter: FavouritesActivityPresenter) : RecyclerView.Adapter<FavouritesActivityAdapter.ViewHolder>() {

	private lateinit var itemSelectCallback: ItemSelectCallback

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesActivityAdapter.ViewHolder {
		val v = LayoutInflater.from(parent.context).inflate(R.layout.single_favourite_layout,
				parent, false)

		return ViewHolder(v, itemSelectCallback)
	}

	override fun getItemCount(): Int = presenter.getCount()

	override fun onBindViewHolder(holder: FavouritesActivityAdapter.ViewHolder, position: Int) {
		presenter.initView(position, holder)
	}

	fun setCallback(itemSelectCallback: ItemSelectCallback) {
		this.itemSelectCallback = itemSelectCallback
	}

	class ViewHolder(v: View, itemSelectCallback: ItemSelectCallback) : RecyclerView.ViewHolder(v), FavouriteRowView {

		private val favouritesIcon: SimpleDraweeView by lazy { v.findViewById<SimpleDraweeView>(R.id.favouriteIcon) }
		private val deleteButton: ImageButton by lazy { v.findViewById<ImageButton>(R.id.delete_btn) }
		private val nameTxt: TextView by lazy { v.findViewById<TextView>(R.id.full_name_txt) }
		private val symbolTxt: TextView by lazy { v.findViewById<TextView>(R.id.symbol_txt) }

		init {
			itemView.setOnClickListener {
				itemSelectCallback.selectFavourite(adapterPosition)
			}

			deleteButton.setOnClickListener {
				itemSelectCallback.deleteFavourite(adapterPosition)
			}
		}

		override fun setIcon(icon: String) {
			favouritesIcon.setImageURI(icon)
		}

		override fun setSymbol(symbol: String) {
			symbolTxt.text = symbol
		}

		override fun setFullName(name: String) {
			nameTxt.text = name
		}
	}


}