package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.compareApiDeprecated
import cryptomarket.eoinahern.ie.cryptomarket.data.models.*
import javax.inject.Inject


class MainActivityAdapter @Inject constructor(private val presenter: MainActivityPresenter, val context: Context)
	: RecyclerView.Adapter<MainActivityAdapter.ViewHolder>(), Filterable {

	private var cryptoData: MutableList<CoinMarketCrypto> = mutableListOf()
	private var initialData: MutableList<CoinMarketCrypto> = mutableListOf()
	private lateinit var currencyStr: String

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		bindCryptoItemView(holder, position)
	}

	private fun bindCryptoItemView(holder: ViewHolder, position: Int) {

		/*val cryptoCurrencyData = cryptoData[position]?.first
		val fullPriceMap = cryptoData[position]?.second
		val currency = fullPriceMap?.get(currencyStr)*/

		/*holder.name.text = cryptoCurrencyData?.Symbol
		holder.fullName.text = cryptoCurrencyData?.FullName
		val url = compareApiDeprecated.plus(cryptoCurrencyData?.ImageUrl)
		holder.price.text = currency?.PRICE
		holder.pctChange.text = String.format(context.getString(R.string.pct_format), currency?.CHANGEPCT24HOUR)
		holder.pctChange.isSelected = currency?.isMinus() ?: false
		holder.icon.setImageURI(url)
		holder.itemView.setOnClickListener {
			presenter.navigateToDetail(cryptoCurrencyData, currency)
        }*/

		val data = cryptoData[position]
		val quote = data.quotes["USD"]

		holder.fullName.text = data.name
		holder.name.text = data.symbol
		holder.pctChange.text = String.format(context.getString(R.string.pct_format), quote?.percent_change_24h)
		holder.pctChange.isSelected = quote?.isMinus() ?: false
		holder.price.text = quote?.price.toString()
		holder.icon.setImageURI(data.getIconUrl())
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

		val v = LayoutInflater.from(parent.context).inflate(R.layout.single_crypto_layout, parent, false)
		return ViewHolder(v)
	}

	override fun getItemCount() = cryptoData.size

	fun updateCryptoData(dataList: List<CoinMarketCrypto>) {

		val insertIndex = cryptoData.size
		cryptoData.addAll(dataList)
		notifyItemRangeInserted(insertIndex, dataList.size)
	}

	fun showFilteredList(dataList: List<CoinMarketCrypto>) {

		if (cryptoData.size > 0)
			cryptoData.clear()

		cryptoData.addAll(dataList)
		notifyDataSetChanged()
	}

	fun setInitData(dataList: List<CoinMarketCrypto>) = initialData.addAll(dataList)

	fun setCurrency(currecyStr: String) {
		currencyStr = currecyStr
		notifyDataSetChanged()
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

	class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
		val icon: SimpleDraweeView by lazy { item.findViewById<SimpleDraweeView>(R.id.crypto_icon) }
		val name: TextView by lazy { item.findViewById<TextView>(R.id.name_abbr) }
		val fullName: TextView by lazy { item.findViewById<TextView>(R.id.full_name) }
		val price: TextView by lazy { item.findViewById<TextView>(R.id.price) }
		val pctChange: TextView by lazy { item.findViewById<TextView>(R.id.percent_txt) }
	}
}