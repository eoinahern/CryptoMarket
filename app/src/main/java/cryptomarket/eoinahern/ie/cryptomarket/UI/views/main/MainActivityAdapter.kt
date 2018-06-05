package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.UI.util.compareApiDeprecated
import cryptomarket.eoinahern.ie.cryptomarket.UI.util.compareApiEndPoint
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyFullPriceDataDisplay
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyPriceConversions
import javax.inject.Inject


class MainActivityAdapter @Inject constructor(private val presenter: MainActivityPresenter, val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	private val VIEW_CRYPTO = 0
	private val VIEW_LOADING = 1

	private var cryptoData: MutableList<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>?> = mutableListOf()
	private lateinit var currencyStr: String

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

		if (getItemViewType(position) == VIEW_CRYPTO) {
			bindCryptoItemView(holder as ViewHolder, position)
		}
	}

	private fun bindCryptoItemView(holder: ViewHolder, position: Int) {

		val cryptoCurrencyData = cryptoData[position]?.first
		val fullPriceMap = cryptoData[position]?.second

		holder.name.text = cryptoCurrencyData?.Symbol
		holder.fullName.text = cryptoCurrencyData?.FullName
		val url = compareApiDeprecated.plus(cryptoCurrencyData?.ImageUrl)
		holder.price.text = fullPriceMap?.get(currencyStr)?.PRICE
		holder.pctChange.text = String.format(context.getString(R.string.pct_format), fullPriceMap?.get(currencyStr)?.CHANGEPCT24HOUR)
		holder.icon.setImageURI(url)
		holder.itemView.setOnClickListener { presenter.navigateToDetail() }
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

		val vh: RecyclerView.ViewHolder

		if (viewType == VIEW_CRYPTO) {

			val v = LayoutInflater.from(parent.context).inflate(R.layout.single_crypto_layout, parent, false)
			vh = ViewHolder(v)
			vh.itemView.setOnClickListener {
				presenter.navigateToDetail()
			}
		} else {

			val v = LayoutInflater.from(parent.context).inflate(R.layout.page_loading_layout, parent, false)
			vh = LoadingViewHolder(v)
		}

		return vh
	}

	fun showLoadingItems() {

		cryptoData.add(cryptoData.size, null)
		notifyItemChanged(cryptoData.size - 1)
	}

	fun removeLoadingItems() {

		cryptoData.removeAt(cryptoData.size - 1)
		notifyItemChanged(cryptoData.size)
	}

	override fun getItemCount(): Int {
		return cryptoData.size
	}


	fun updateCryptoData(dataList: List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>>) {

		cryptoData.addAll(dataList)
		notifyDataSetChanged()
	}

	fun setCurrency(currecyStr: String) {

		currencyStr = currecyStr
		notifyDataSetChanged()
	}

	override fun getItemViewType(position: Int) = if (cryptoData[position] != null) VIEW_CRYPTO else VIEW_LOADING

	class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
		val icon: SimpleDraweeView by lazy { item.findViewById<SimpleDraweeView>(R.id.crypto_icon) }
		val name: TextView by lazy { item.findViewById<TextView>(R.id.name_abbr) }
		val fullName: TextView by lazy { item.findViewById<TextView>(R.id.full_name) }
		val price: TextView by lazy { item.findViewById<TextView>(R.id.price) }
		val pctChange: TextView by lazy { item.findViewById<TextView>(R.id.percent_txt) }
	}

	class LoadingViewHolder(item: View) : RecyclerView.ViewHolder(item)
}