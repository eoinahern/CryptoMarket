package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import cryptomarket.eoinahern.ie.cryptomarket.UI.base.MvpView
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyFullPriceDataDisplay
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyPriceConversions


interface MainActivityView : MvpView {

	fun showLoading()

	fun hideLoading()

	fun updateRecyclerView(dataList: List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>?>)

	fun showNetworkError()

	fun showOtherError()

	fun gotToDetail(crypto: CryptoCurrency?, currencyFullPrice: CurrencyFullPriceDataDisplay?)


}