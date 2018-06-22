package cryptomarket.eoinahern.ie.cryptomarket.domain.details

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.models.HistoricalData
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import io.reactivex.functions.Function3
import retrofit2.Response
import javax.inject.Inject

@PerScreen
class GetGraphDataInteractor @Inject constructor(private val cryptoApi: CryptoApi) : BaseInteractor<List<Response<HistoricalData>>>() {

	private lateinit var cryptoAbv: String
	private lateinit var convertedTo: String

	fun setSearchCrypto(cryptoAbv: String, convertedTo: String): GetGraphDataInteractor {

		this.cryptoAbv = cryptoAbv
		this.convertedTo = convertedTo
		return this
	}

	override fun buildObservable(): Observable<List<Response<HistoricalData>>> {

		return Observable.zip(cryptoApi.getHistoricalDataDay(cryptoAbv, convertedTo, "30"),
				cryptoApi.getHistoricalDataHour(cryptoAbv, convertedTo, "12"),
				cryptoApi.getHistoricalDataHour(cryptoAbv, convertedTo, "24"),
				Function3<Response<HistoricalData>,
						Response<HistoricalData>, Response<HistoricalData>, List<Response<HistoricalData>>>
				{ T1, T2, T3 -> listOf(T1, T2, T3) })
	}

}

