package cryptomarket.eoinahern.ie.cryptomarket.domain.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CoinMarketCapApi
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.models.*

import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import io.reactivex.functions.Function7
import io.reactivex.functions.Function3
import javax.inject.Inject

@PerScreen
class GetCryptoListInteractor @Inject constructor(private val cryptoApi: CryptoApi,
												  private val coinMarketCapApi: CoinMarketCapApi) :
		BaseInteractor<List<CoinMarketCrypto>>() {

	override fun buildObservable(): Observable<List<CoinMarketCrypto>> {
		return Observable.zip(

				Observable.zip(coinMarketCapApi.getTickerData("1", "100"),
						coinMarketCapApi.getTickerData("101", "100"),
						coinMarketCapApi.getTickerData("201", "100"),
						coinMarketCapApi.getTickerData("301", "100"),
						coinMarketCapApi.getTickerData("401", "100"),
						coinMarketCapApi.getTickerData("501", "100"),
						coinMarketCapApi.getTickerData("601", "100"),
						Function7<CoinMarketTickerData, CoinMarketTickerData, CoinMarketTickerData,
								CoinMarketTickerData, CoinMarketTickerData, CoinMarketTickerData,
								CoinMarketTickerData, List<CoinMarketCrypto>> { t1, t2, t3, t4, t5, t6, t7 ->
							listOf(t1.data.values, t2.data.values, t3.data.values, t4.data.values
									, t5.data.values, t6.data.values, t7.data.values).flatten()
						}),

				Observable.zip(coinMarketCapApi.getTickerData("701", ""),
						coinMarketCapApi.getTickerData("801", "100"),
						coinMarketCapApi.getTickerData("901", "100"),
						coinMarketCapApi.getTickerData("1001", "100"),
						coinMarketCapApi.getTickerData("1101", "100"),
						coinMarketCapApi.getTickerData("1201", "100"),
						coinMarketCapApi.getTickerData("1301", "100"),
						Function7<CoinMarketTickerData, CoinMarketTickerData, CoinMarketTickerData,
								CoinMarketTickerData, CoinMarketTickerData, CoinMarketTickerData,
								CoinMarketTickerData, List<CoinMarketCrypto>> { t1, t2, t3, t4, t5, t6, t7 ->
							listOf(t1.data.values, t2.data.values, t3.data.values, t4.data.values
									, t5.data.values, t6.data.values, t7.data.values).flatten()
						}),

				Observable.zip(coinMarketCapApi.getTickerData("1401", "100"),
						coinMarketCapApi.getTickerData("1501", "100"),
						coinMarketCapApi.getTickerData("1601", "100"),
						Function3<CoinMarketTickerData, CoinMarketTickerData, CoinMarketTickerData,
								List<CoinMarketCrypto>> { z1, z2, z3 ->
							listOf(z1.data.values, z2.data.values, z3.data.values).flatten()
						})
				, Function3<List<CoinMarketCrypto>, List<CoinMarketCrypto>, List<CoinMarketCrypto>, List<CoinMarketCrypto>> { x1, x2, x3 ->
			listOf(x1, x2, x3).flatten()
		})
	}

	//private var offset: Int = -1
	//private var limit: Int = -1

	/*fun setStartLimit(offset: Int, limit: Int): GetCryptoListInteractor {
		//this.offset = offset
		this.limit = limit
		return this
	}*/

	/*override fun buildObservable(): Observable<List<Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>?>> {


		return cryptoApi.getList().cache().onTerminateDetach().flatMap { currencyData ->
			var listOfSymbols = constructList(currencyData)
			var joinedList = listOfSymbols.joinToString(",")

			cryptoApi.getFullPriceData(joinedList, "EUR,USD,BTC,PLN,GBP").map { fullPriceWrapper ->

				var f = LinkedHashMap<String, Pair<CryptoCurrency?, Map<String, CurrencyFullPriceDataDisplay>?>>()

				for (symbol in listOfSymbols) {

					if (fullPriceWrapper.DISPLAY.contains(symbol) && currencyData.cryptoWrapper.contains(symbol)) {
						f[symbol] = Pair(currencyData.cryptoWrapper[symbol], fullPriceWrapper.DISPLAY[symbol]?.item)
					}
				}

				f.values.toList()
			}
		}
	}*/

	/*private fun constructList(currencyData: CurrencyData): List<String> {

		var sortedList: List<CryptoCurrency> = currencyData.cryptoWrapper.values.toList().sorted()
		return sortedList.map { it.Symbol }.subList(offset, limit + offset)
	}*/

}