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
	: BaseInteractor<HashMap<String, Pair<CryptoCurrency, CurrencyPriceConversions>>>() {

	override fun buildObservable(): Observable<HashMap<String, Pair<CryptoCurrency, CurrencyPriceConversions>>> {

		return cryptoApi.getList().flatMap { currencyData ->

			val cryptoInputStr = constructList(currencyData)
			println(cryptoInputStr)
			cryptoApi.getPriceData(cryptoInputStr, "EUR,USD").map { innerMap ->

				val finalMap = HashMap<String, Pair<CryptoCurrency, CurrencyPriceConversions>>()

				for ((key, value) in currencyData.cryptoWrapper) {
					innerMap[key]?.let { currencyPriceConv ->
						finalMap.put(key, Pair(value, currencyPriceConv))
					}
				}
				finalMap
			}
		}


		/*Observable.zip(
				cryptoApi.getList(),
				cryptoApi.getPricing(),
				BiFunction { v1: CurrencyData, v2: CryptoCurrency -> Pair(v1, v2) })*/


		//BiFunction<CurrencyData,CryptoCurrency, Pair<CurrencyData,CryptoCurrency>>{ a, b -> Pair(a,b)})
	}


	private fun constructList(currencyData: CurrencyData) = currencyData.cryptoWrapper.map { it.value.Symbol }.joinToString(",")


}