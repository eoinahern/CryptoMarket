package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import android.widget.Filter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyFullPriceDataDisplay

class MainActivityCryptoFilter(private val dataList : List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>?>) : Filter() {

	override fun publishResults(searchSequence: CharSequence?, p1: FilterResults?) {
		//not required at present
	}

	override fun performFiltering(searchSequence: CharSequence?): FilterResults? {
		var filteredResults = FilterResults()
		val searchStr = searchSequence.toString()

		filteredResults.count = dataList.size
		filteredResults.values = dataList.filter{
			it?.first?.FullName?.contains(searchStr) ?: false
		}
		return filteredResults
	}


}