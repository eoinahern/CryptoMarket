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
	: BaseInteractor<HashMap<String, Pair<CryptoCurrency?, CurrencyPriceConversions?>>>() {

	private var offset: Int = -1
	private var limit: Int = -1

	fun setStartLimir(offset: Int, limit: Int): GetCryptoListInteractor {
		this.offset = offset
		this.limit = limit
		return this
	}

	override fun buildObservable(): Observable<HashMap<String, Pair<CryptoCurrency?, CurrencyPriceConversions?>>> {

		return cryptoApi.getList().flatMap { currencyData ->

			val listOfSymbols = constructList(currencyData)

			cryptoApi.getPriceData(listOfSymbols.joinToString(","), "EUR,USD,BTC,PLN,INR,KRW").map { innerMap ->

				val finalMap = HashMap<String, Pair<CryptoCurrency?, CurrencyPriceConversions?>>()
				for (symbol in listOfSymbols) {


					if (innerMap.contains(symbol) && currencyData.cryptoWrapper.containsKey(symbol)) {
						finalMap[symbol] = Pair(currencyData.cryptoWrapper[symbol], innerMap[symbol])
					}
				}

				finalMap
			}
		}
	}

	private fun constructList(currencyData: CurrencyData) = currencyData.cryptoWrapper.map { it.value.Symbol }.subList(offset, limit)

}