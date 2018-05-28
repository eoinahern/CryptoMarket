package cryptomarket.eoinahern.ie.cryptomarket.domain.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.api.MinApiCryptoCompare
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyData
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyPriceConversions
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.BiFunction
import javax.inject.Inject

@PerScreen
class GetCryptoListInteractor @Inject constructor(private val cryptoApi: CryptoApi)
	: BaseInteractor<List<Pair<CryptoCurrency?, CurrencyPriceConversions?>>>() {

	private var offset: Int = -1
	private var limit: Int = -1
	private lateinit var currData: CurrencyData

	fun setStartLimit(offset: Int, limit: Int): GetCryptoListInteractor {
		this.offset = offset
		this.limit = limit
		return this
	}

	override fun buildObservable(): Observable<List<Pair<CryptoCurrency?, CurrencyPriceConversions?>>> {

		return try {
			getList(currData)
		} catch (e: UninitializedPropertyAccessException) {
			cryptoApi.getList().flatMap { currencyData ->
				currData = currencyData
				getList(currData)
			}
		}
	}

	private fun getList(currencyData: CurrencyData): Observable<List<Pair<CryptoCurrency?, CurrencyPriceConversions?>>> {

		val listOfSymbols = constructList(currencyData)

		return cryptoApi.getPriceData(listOfSymbols.joinToString(","), "EUR,USD,BTC,PLN,INR,KRW").map { innerMap ->

			val finalMap = HashMap<String, Pair<CryptoCurrency?, CurrencyPriceConversions?>>()
			for (symbol in listOfSymbols) {


				if (innerMap.contains(symbol) && currencyData.cryptoWrapper.contains(symbol)) {
					finalMap[symbol] = Pair(currencyData.cryptoWrapper[symbol], innerMap[symbol])
				}
			}

			finalMap.values.toList()
		}
	}

	private fun constructList(currencyData: CurrencyData) = currencyData.cryptoWrapper.map { it.value.Symbol }.subList(offset, limit)

}