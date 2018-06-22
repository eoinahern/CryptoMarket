package cryptomarket.eoinahern.ie.cryptomarket.domain.details

import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.models.HistoricalData
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

import org.mockito.MockitoAnnotations
import retrofit2.Response
import kotlin.test.assertEquals

class GetGraphDataInteractorTest {

	@Mock
	lateinit var cryptoApi: CryptoApi

	@Mock
	lateinit var dayObs : Observable<Response<HistoricalData>>

	@Mock
	lateinit var hourObs1 : Observable<Response<HistoricalData>>

	@Mock
	lateinit var hourObs2 : Observable<Response<HistoricalData>>

	private val currency = "BTC"
	private val converted = "EUR"

	lateinit var getGraphDataInteractor: GetGraphDataInteractor

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		getGraphDataInteractor = GetGraphDataInteractor(cryptoApi)
	}

	@Test
	fun buildObservableTest() {

		`when`(cryptoApi.getHistoricalDataDay(currency, converted, "30")).thenReturn(dayObs)
		`when`(cryptoApi.getHistoricalDataHour(currency, converted, "12")).thenReturn(hourObs1)
		`when`(cryptoApi.getHistoricalDataHour(currency, converted, "24")).thenReturn(hourObs2)

		getGraphDataInteractor.setSearchCrypto(currency, converted).buildObservable()

		verify(cryptoApi).getHistoricalDataHour(currency, converted, "12")
		verify(cryptoApi).getHistoricalDataHour(currency, converted, "24")
		verify(cryptoApi).getHistoricalDataDay(currency, converted, "30")

	}
}