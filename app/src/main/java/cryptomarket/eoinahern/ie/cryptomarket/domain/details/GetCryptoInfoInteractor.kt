package cryptomarket.eoinahern.ie.cryptomarket.domain.details

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApiOld
import cryptomarket.eoinahern.ie.cryptomarket.data.models.*
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

@PerScreen
class GetCryptoInfoInteractor @Inject constructor(private val cryptoApiOld: CryptoApiOld,
												  private val cryptoApi: CryptoApi) :
		BaseInteractor<Pair<SnapShotData, CurrencyFullPriceDataDisplay?>>() {

	private lateinit var id: String
	private lateinit var toCurrency: String
	private lateinit var cryptoAbv: String

	fun setID(id: String, cryptoAbv: String, toCurrency: String): GetCryptoInfoInteractor {
		this.id = id
		this.toCurrency = toCurrency
		this.cryptoAbv = cryptoAbv
		return this
	}

	override fun buildObservable(): Observable<Pair<SnapShotData, CurrencyFullPriceDataDisplay?>> {
		return Observable.zip(cryptoApi.getFullPriceData(cryptoAbv, toCurrency),
				cryptoApiOld.getCoinSnapShotById(id),
				BiFunction<FullPriceWrapper, CoinFullSnapShot, Pair<SnapShotData, CurrencyFullPriceDataDisplay?>> { price, snapShot ->

					val fullPriceWrapper = price.DISPLAY[cryptoAbv]
					val fullPriceData = fullPriceWrapper?.item?.get(toCurrency)

					Pair(snapShot.Data, fullPriceData)
				})
	}
}
