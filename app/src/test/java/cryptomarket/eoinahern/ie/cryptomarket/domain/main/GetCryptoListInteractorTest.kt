package cryptomarket.eoinahern.ie.cryptomarket.domain.main

import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.models.*
import de.jodamob.kotlin.testrunner.KotlinTestRunner
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals


@RunWith(KotlinTestRunner::class)
class GetCryptoListInteractorTest {

	@Mock
	lateinit var mockCryptoApi: CryptoApi
	var currencyData : CurrencyData? = null

	@Mock
	lateinit var mockCurrData: CurrencyData

	@Mock
	lateinit var mockFullPriceWrapper : FullPriceWrapper
	@Mock
	lateinit var mockFullPriceWrapperInternalBTC : FullPriceWrapperInternalDisplay
	@Mock
	lateinit var mockFullPriceWrapperInternalETH : FullPriceWrapperInternalDisplay
	@Mock
	lateinit var mockFullPriceWrapperInternalLTC : FullPriceWrapperInternalDisplay
	@Mock
	lateinit var mockFullPriceWrapperInternalDASH: FullPriceWrapperInternalDisplay
	@Mock
	lateinit var mockMapInternal : LinkedHashMap<String, FullPriceWrapperInternalDisplay>


	lateinit var cryptoInteractor: GetCryptoListInteractor

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)

		Mockito.`when`(mockCryptoApi.getList()).thenReturn(Observable.just(getDataHelper()))
		cryptoInteractor = GetCryptoListInteractor(mockCryptoApi).setStartLimit(0,4)
	}

    //test is a bit verbose.
	@Test
	fun buildObservableTest() {

		`when`(mockCryptoApi.getFullPriceData(anyString() , anyString())).thenReturn(Observable.just(mockFullPriceWrapper))
		`when`(mockFullPriceWrapper.DISPLAY).thenReturn(mockMapInternal)
		`when`(mockMapInternal.contains(anyString())).thenReturn(true)
		`when`(mockMapInternal["BTC"]).thenReturn(mockFullPriceWrapperInternalBTC)
		`when`(mockMapInternal["ETH"]).thenReturn(mockFullPriceWrapperInternalETH)
		`when`(mockMapInternal["LTC"]).thenReturn(mockFullPriceWrapperInternalLTC)
		`when`(mockMapInternal["DASH"]).thenReturn(mockFullPriceWrapperInternalDASH)
		`when`(mockFullPriceWrapperInternalBTC.item).thenReturn(mutableMapOf("BTC" to CurrencyFullPriceDataDisplay("BTC", "1.00", "1")))
		`when`(mockFullPriceWrapperInternalETH.item).thenReturn(mutableMapOf("ETH" to CurrencyFullPriceDataDisplay("ETH", "1.00", "1")))
		`when`(mockFullPriceWrapperInternalLTC.item).thenReturn(mutableMapOf("LTC" to CurrencyFullPriceDataDisplay("LTC", "1.00", "1")))
		`when`(mockFullPriceWrapperInternalDASH.item).thenReturn(mutableMapOf("DASH" to CurrencyFullPriceDataDisplay("DASH", "1.00", "1")))

		val list = cryptoInteractor.buildObservable().blockingFirst()

		assertEquals(4, list.size)
		assertEquals("BTC",list[0]?.first?.Symbol)
		assertEquals("ETH",list[1]?.first?.Symbol)


		verify(mockCryptoApi).getList()
		verify(mockCryptoApi).getFullPriceData( "BTC,ETH,LTC,DASH", "EUR,USD,BTC,PLN,GBP")

	}

	private fun getDataHelper() : CurrencyData? {

		val cryptoBase = CryptoCurrency("1", "url", "url", "bitcoin",
				"BTC","bitcoin", "bitcoin", "1")

		val cryptoEth = cryptoBase.copy(Symbol = "ETH", SortOrder = "2")
        val cryptMon = cryptoBase.copy(Symbol = "LTC", SortOrder = "3")
        val dash = cryptoBase.copy(Symbol = "DASH", SortOrder = "4")
		val cryptoMap = HashMap<String , CryptoCurrency>()

		cryptoMap[cryptoBase.Symbol] = cryptoBase
		cryptoMap[cryptoEth.Symbol] = cryptoEth
		cryptoMap[cryptMon.Symbol] = cryptMon
		cryptoMap[dash.Symbol] = dash

		currencyData = CurrencyData("r", "message", "url", cryptoMap)
		return currencyData

	}

}