package cryptomarket.eoinahern.ie.cryptomarket.domain.main

import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyData
import de.jodamob.kotlin.testrunner.KotlinTestRunner
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


@RunWith(KotlinTestRunner::class)
class GetCryptoListInteractorTest {

	@Mock
	lateinit var mockCryptoApi: CryptoApi

	@Mock
	lateinit var mockCurrData : CurrencyData
	lateinit var cryptoInteractor: GetCryptoListInteractor

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)

		Mockito.`when`(mockCryptoApi.getList()).thenReturn(Observable.just(mockCurrData))
		cryptoInteractor = GetCryptoListInteractor(mockCryptoApi)
	}


	@Test
	fun buildObservableTest() {

		cryptoInteractor.buildObservable()
		verify(mockCryptoApi).getList()

	}

	private fun getDataHelper() {



	}

}