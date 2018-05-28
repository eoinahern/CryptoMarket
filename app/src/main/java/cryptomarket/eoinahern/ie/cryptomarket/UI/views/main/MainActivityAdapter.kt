package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

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
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyPriceConversions
import javax.inject.Inject


class MainActivityAdapter @Inject constructor(val presenter: MainActivityPresenter) : RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {

	private var cryptoData: MutableList<Pair<CryptoCurrency?, CurrencyPriceConversions?>> = mutableListOf()

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {

		holder.name.text = cryptoData[position].first?.Symbol
		holder.fullName.text = cryptoData[position].first?.FullName
		val url = compareApiDeprecated.plus(cryptoData[position].first?.ImageUrl)

		holder.icon.setImageURI(url)
		holder.itemView.setOnClickListener { presenter.navigateToDetail() }
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

		val v = LayoutInflater.from(parent.context).inflate(R.layout.single_crypto_layout, parent, false)
		val vh = ViewHolder(v)
		vh.itemView.setOnClickListener { presenter.navigateToDetail() }

		return vh
	}

	override fun getItemCount(): Int {
		return cryptoData.size
	}


	fun updateCryptoData(dataMap: List<Pair<CryptoCurrency?, CurrencyPriceConversions?>>) {

		if (cryptoData.isNotEmpty()) {
			cryptoData.clear()
		}

		cryptoData.addAll(dataMap)
		notifyDataSetChanged()
	}

	class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
		val icon: SimpleDraweeView by lazy { item.findViewById<SimpleDraweeView>(R.id.crypto_icon) }
		val name: TextView by lazy { item.findViewById<TextView>(R.id.name_abbr) }
		val fullName: TextView by lazy { item.findViewById<TextView>(R.id.full_name) }
	}


}