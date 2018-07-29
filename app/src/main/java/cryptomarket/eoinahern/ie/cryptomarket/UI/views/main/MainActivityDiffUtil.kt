package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import android.support.v7.util.DiffUtil
import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CoinMarketCrypto
import javax.inject.Inject

@PerScreen
class MainActivityDiffUtil @Inject constructor() : DiffUtil.Callback() {

	private var oldList: MutableList<CoinMarketCrypto> = mutableListOf()
	private var newList: MutableList<CoinMarketCrypto> = mutableListOf()
	private lateinit var symbol: String

	fun setData(oldList: List<CoinMarketCrypto>, newList: List<CoinMarketCrypto>, symbol: String) {
		this.oldList.clear()
		this.newList.clear()
		this.oldList.addAll(oldList)
		this.newList.addAll(newList)
		this.symbol = symbol
	}

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].symbol == newList[newItemPosition].symbol

	override fun getOldListSize() = oldList.size

	override fun getNewListSize() = newList.size

	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		val oldItem = oldList[oldItemPosition]
		val newItem = newList[newItemPosition]
		return oldItem.equals(newItem)
	}
}