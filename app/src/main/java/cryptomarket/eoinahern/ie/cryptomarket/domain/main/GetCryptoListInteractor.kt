package cryptomarket.eoinahern.ie.cryptomarket.domain.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.models.*
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseDisposableObserver
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import java.util.*
import javax.inject.Inject

@PerScreen
class GetCryptoListInteractor @Inject constructor(private val cryptoApi: CryptoApi) :
		BaseInteractor<List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>?>>() {

	private var offset: Int = -1
	private var limit: Int = -1
	private lateinit var currData: CurrencyData

	fun setStartLimit(offset: Int, limit: Int): GetCryptoListInteractor {
		this.offset = offset
		this.limit = limit
		return this
	}

	override fun buildObservable(): Observable<List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>?>> {

		/*return try {
			getList(currData)
		} catch (e: UninitializedPropertyAccessException) {*/
			return cryptoApi.getList().flatMap { currencyData ->
				//currData = currencyData
				getList(currencyData)
			}
		//}
	}

	private fun getList(currencyData: CurrencyData): Observable<List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>?>> {

		var listOfSymbols = constructList(currencyData)
		var joinedList = listOfSymbols.joinToString(",")

		return cryptoApi.getFullPriceData(joinedList, "EUR,USD,BTC,PLN,GBP").map { fullPriceWrapper ->

					var f = LinkedHashMap<String, Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>>()

					for (symbol in listOfSymbols) {

						if (fullPriceWrapper.DISPLAY.contains(symbol) && currencyData.cryptoWrapper.contains(symbol)) {
							f[symbol] = Pair(currencyData.cryptoWrapper[symbol], fullPriceWrapper.DISPLAY[symbol]?.item)
						}
					}

					f.values.toList()
				}
	}

	private fun constructList(currencyData: CurrencyData): List<String> {

		var sortedList : List<CryptoCurrency>  = currencyData.cryptoWrapper.values.toList().sorted()
		return sortedList.map{ it.Symbol }.subList(offset, limit + offset)
	}

}