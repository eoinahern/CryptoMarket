package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import android.content.Context
import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.data.models.*
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.PCT_CHANGE_24H
import javax.inject.Inject

@PerScreen
class MainActivityAdapter @Inject constructor(private val presenter: MainActivityPresenter,
											  private val context: Context)
	: RecyclerView.Adapter<MainActivityAdapter.ViewHolder>(), Filterable {

	private var cryptoData: MutableList<CoinMarketCrypto> = mutableListOf()
	private var initialData: MutableList<CoinMarketCrypto> = mutableListOf()
	private lateinit var currencyStr: String

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		bindCryptoItemView(holder, position)
	}

	private fun bindCryptoItemView(holder: ViewHolder, position: Int) {
		val data = cryptoData[position]
		val quote = data.quotes[currencyStr]

		holder.fullName.text = data.name
		holder.name.text = data.symbol
		holder.pctChange.text = String.format(context.getString(R.string.pct_format), quote?.percent_change_24h)
		holder.pctChange.isSelected = quote?.isMinus() ?: false
		holder.price.text = String.format(context.getString(R.string.simple_price_frmt),
				currencyStr, quote?.price.toString())
		holder.icon.setImageURI(data.getIconUrl())

		holder.itemView.setOnClickListener {
			presenter.navigateToDetail(data.symbol)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val v = LayoutInflater.from(parent.context)
				.inflate(R.layout.single_crypto_layout, parent, false)
		return ViewHolder(v)
	}

	override fun getItemCount() = cryptoData.size

	fun updateCryptoData(dataList: List<CoinMarketCrypto>) {
		cryptoData.clear()
		cryptoData.addAll(dataList)
		notifyItemRangeInserted(0, dataList.size)
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
		val size = cryptoData.size
		cryptoData.clear()
		initialData.clear()
		notifyItemRangeRemoved(0, size)
	}

	class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
		val icon: SimpleDraweeView by lazy { item.findViewById<SimpleDraweeView>(R.id.crypto_icon) }
		val name: TextView by lazy { item.findViewById<TextView>(R.id.name_abbr) }
		val fullName: TextView by lazy { item.findViewById<TextView>(R.id.full_name) }
		val price: TextView by lazy { item.findViewById<TextView>(R.id.price) }
		val pctChange: TextView by lazy { item.findViewById<TextView>(R.id.percent_txt) }
	}
}