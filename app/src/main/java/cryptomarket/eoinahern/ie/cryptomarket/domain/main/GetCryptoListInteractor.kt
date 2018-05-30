package cryptomarket.eoinahern.ie.cryptomarket.domain.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.models.*
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

@PerScreen
class GetCryptoListInteractor @Inject constructor(private val cryptoApi: CryptoApi) :
		BaseInteractor<List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>>>() {

	private var offset: Int = -1
	private var limit: Int = -1
	private lateinit var currData: CurrencyData

	fun setStartLimit(offset: Int, limit: Int): GetCryptoListInteractor {
		this.offset = offset
		this.limit = limit
		return this
	}

	override fun buildObservable(): Observable<List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>>> {


		Observable.just(Unit)
		return try {
			getList(currData)
		} catch (e: UninitializedPropertyAccessException) {
			cryptoApi.getList().flatMap { currencyData ->
				currData = currencyData
				getList(currData)
			}
		}
	}

	private fun getList(currencyData: CurrencyData): Observable<List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>>> {

		val listOfSymbols = constructList(currencyData)
		println(listOfSymbols.joinToString(","))


		return cryptoApi.getFullPriceData(listOfSymbols.joinToString(","), "EUR,USD") //,BTC,PLN,INR,KRW
				.map { fullPriceWrapper ->

					println(fullPriceWrapper.DISPLAY)
					val f = HashMap<String, Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>>()

					for (symbol in listOfSymbols) {

						if (fullPriceWrapper.DISPLAY.contains(symbol) && currencyData.cryptoWrapper.contains(symbol)) {
							f[symbol] = Pair(currencyData.cryptoWrapper[symbol], fullPriceWrapper.DISPLAY[symbol]?.displayMapToCurrency)
						}
					}


					f.values.toList().sortedBy { p -> p.first?.Symbol }
				}
	}

	private fun constructList(currencyData: CurrencyData) = currencyData.cryptoWrapper.toSortedMap().map { it.value.Symbol }.subList(offset, limit)

}