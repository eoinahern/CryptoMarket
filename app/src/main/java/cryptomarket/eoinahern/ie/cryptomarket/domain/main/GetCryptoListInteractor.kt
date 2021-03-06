package cryptomarket.eoinahern.ie.cryptomarket.domain.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CoinMarketCapApi
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.models.*

import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import io.reactivex.functions.Function7
import io.reactivex.functions.Function3
import io.reactivex.functions.Function4
import javax.inject.Inject

@PerScreen
class GetCryptoListInteractor @Inject constructor(private val coinMarketCapApi: CoinMarketCapApi) :
		BaseInteractor<List<CoinMarketCrypto>>() {

	private lateinit var currency: String

	fun setCurrency(currency: String): GetCryptoListInteractor {
		this.currency = currency
		return this
	}

	override fun buildObservable(): Observable<List<CoinMarketCrypto>> {

		return Observable.zip(
				Observable.zip(coinMarketCapApi.getTickerData("1", currency),
						coinMarketCapApi.getTickerData("101", currency),
						coinMarketCapApi.getTickerData("201", currency),
						coinMarketCapApi.getTickerData("301", currency),
						coinMarketCapApi.getTickerData("401", currency),
						coinMarketCapApi.getTickerData("501", currency),
						coinMarketCapApi.getTickerData("601", currency),
						Function7<CoinMarketTickerData, CoinMarketTickerData, CoinMarketTickerData,
								CoinMarketTickerData, CoinMarketTickerData, CoinMarketTickerData,
								CoinMarketTickerData, List<CoinMarketCrypto>> { t1, t2, t3, t4, t5, t6, t7 ->
							listOf(t1.data.values, t2.data.values, t3.data.values, t4.data.values
									, t5.data.values, t6.data.values, t7.data.values).flatten()
						}),

				Observable.zip(coinMarketCapApi.getTickerData("701", currency),
						coinMarketCapApi.getTickerData("801", currency),
						coinMarketCapApi.getTickerData("901", currency),
						coinMarketCapApi.getTickerData("1001", currency),
						coinMarketCapApi.getTickerData("1101", currency),
						coinMarketCapApi.getTickerData("1201", currency),
						coinMarketCapApi.getTickerData("1301", currency),
						Function7<CoinMarketTickerData, CoinMarketTickerData, CoinMarketTickerData,
								CoinMarketTickerData, CoinMarketTickerData, CoinMarketTickerData,
								CoinMarketTickerData, List<CoinMarketCrypto>> { t1, t2, t3, t4, t5, t6, t7 ->
							listOf(t1.data.values, t2.data.values, t3.data.values, t4.data.values
									, t5.data.values, t6.data.values, t7.data.values).flatten()
						}),

				Observable.zip(coinMarketCapApi.getTickerData("1401", currency),
						coinMarketCapApi.getTickerData("1501", currency),
						coinMarketCapApi.getTickerData("1601", currency),
						Function3<CoinMarketTickerData, CoinMarketTickerData, CoinMarketTickerData, List<CoinMarketCrypto>>
						{ z1, z2, z3 ->
							listOf(z1.data.values, z2.data.values, z3.data.values).flatten()
						})
				, Function3<List<CoinMarketCrypto>, List<CoinMarketCrypto>, List<CoinMarketCrypto>,
				List<CoinMarketCrypto>> { x1, x2, x3 ->
			listOf(x1, x2, x3).flatten()
		})
	}
}