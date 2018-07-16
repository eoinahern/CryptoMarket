package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import cryptomarket.eoinahern.ie.cryptomarket.UI.base.MvpView
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CoinMarketCrypto
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyFullPriceDataDisplay
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyPriceConversions


interface MainActivityView : MvpView {

	fun showLoading()

	fun hideLoading()

	fun updateRecyclerView(dataList: List<CoinMarketCrypto>)

	fun showNetworkError()

	fun showOtherError()

	fun gotToDetail(crypto: CryptoCurrency?, currencyFullPrice: CurrencyFullPriceDataDisplay?)

	fun initCurrencyData(currencyData: Map<String, CryptoCurrency>)
}