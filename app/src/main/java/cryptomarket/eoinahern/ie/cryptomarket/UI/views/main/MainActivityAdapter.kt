package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.data.models.*
import javax.inject.Inject

@PerScreen
class MainActivityAdapter @Inject constructor(private val presenter: MainActivityPresenter)
	: RecyclerView.Adapter<MainActivityAdapter.ViewHolder>(), Filterable {

	private var cryptoData: MutableList<CoinMarketCrypto> = mutableListOf()
	private var initialData: MutableList<CoinMarketCrypto> = mutableListOf()
	private lateinit var currencyStr: String
	private lateinit var itemSelectCallback: ItemSelectCallback

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bindData(cryptoData[position], currencyStr)
		holder.favouriteCheckbox.isChecked = presenter.getSelected(cryptoData[position].symbol)
	}

	fun setCallback(itemSelect: ItemSelectCallback) {
		itemSelectCallback = itemSelect
	}

	fun navigateToDetail(position: Int) {
		presenter.navigateToDetail(cryptoData[position].symbol)
	}

	fun favouritesChecked(position: Int, isChecked: Boolean) {
		presenter.updateCurrencyFavourite(cryptoData[position].symbol, isChecked)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val v = LayoutInflater.from(parent.context)
				.inflate(R.layout.single_crypto_layout, parent, false)
		return ViewHolder(v, itemSelectCallback)
	}

	override fun getItemCount() = cryptoData.size

	fun updateCryptoData(dataList: List<CoinMarketCrypto>) {
		updateCryptoList(dataList)
		updateBaseList(dataList)
	}

	private fun updateCryptoList(dataList: List<CoinMarketCrypto>) {
		cryptoData.clear()
		cryptoData.addAll(dataList)
		notifyItemRangeInserted(0, dataList.size)
	}

	private fun updateBaseList(dataList: List<CoinMarketCrypto>) {
		initialData.clear()
		initialData.addAll(dataList)
	}

	fun showFilteredList(dataList: List<CoinMarketCrypto>) {
		if (cryptoData.size > 0)
			cryptoData.clear()

		cryptoData.addAll(dataList)
		notifyDataSetChanged()
	}

	fun setCurrency(currecyStr: String) {
		currencyStr = currecyStr
	}

	override fun getFilter(): Filter {

		return object : Filter() {

			override fun publishResults(searchSequence: CharSequence?, filteredResults: FilterResults?) {
				var list = filteredResults?.values as List<CoinMarketCrypto>
				showFilteredList(list)
			}

			override fun performFiltering(searchSequence: CharSequence?): FilterResults? {
				var filteredResults = FilterResults()

				if (searchSequence?.trim().isNullOrEmpty() || searchSequence?.trim().isNullOrBlank()) {
					filteredResults.count = initialData.size
					filteredResults.values = initialData
					return filteredResults
				}

				val searchStr = searchSequence.toString()
				var filteredList = filterList(searchStr)
				filteredResults.count = filteredList.size
				filteredResults.values = filteredList

				return filteredResults
			}

			private fun filterList(searchStr: String) = cryptoData.filter {
				it.name.startsWith(searchStr, ignoreCase = true)
			}
		}
	}

	fun clear() {
		val size = itemCount
		cryptoData.clear()
		initialData.clear()
		notifyItemRangeRemoved(0, size)
	}

	class ViewHolder(item: View, var itemSelect: ItemSelectCallback) : RecyclerView.ViewHolder(item) {

		val icon: SimpleDraweeView by lazy { item.findViewById<SimpleDraweeView>(R.id.crypto_icon) }
		val name: TextView by lazy { item.findViewById<TextView>(R.id.name_abbr) }
		val fullName: TextView by lazy { item.findViewById<TextView>(R.id.full_name) }
		val price: TextView by lazy { item.findViewById<TextView>(R.id.price) }
		val pctChange: TextView by lazy { item.findViewById<TextView>(R.id.percent_txt) }
		val favouriteCheckbox: CheckBox by lazy { item.findViewById<CheckBox>(R.id.favourites) }

		init {
			favouriteCheckbox.setOnCheckedChangeListener { _, isChecked ->
				itemSelect.favouritesChecked(adapterPosition, isChecked)
			}

			itemView.setOnClickListener {
				itemSelect.cryptoSelected(adapterPosition)
			}
		}

		fun bindData(cryptoItem: CoinMarketCrypto, currencyStr: String) {
			val marketData = cryptoItem.quotes[currencyStr]
			fullName.text = cryptoItem.name
			name.text = cryptoItem.symbol
			pctChange.text = String.format(itemView.context.getString(R.string.pct_format),
					marketData?.percent_change_24h)
			pctChange.isSelected = marketData?.isMinus() ?: false
			price.text = String.format(itemView.context.getString(R.string.simple_price_frmt),
					currencyStr, marketData?.price.toString())
			icon.setImageURI(cryptoItem.getIconUrl())
		}
	}
}