package cryptomarket.eoinahern.ie.cryptomarket.domain.details

import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import org.junit.Assert
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetGraphDataInteractorTest {

	@Mock
	lateinit var cryptoApi: CryptoApi

	lateinit var getGraphDataInteractor: GetGraphDataInteractor

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		getGraphDataInteractor = GetGraphDataInteractor(cryptoApi)
	}


	@Test
	fun buildObservableTest() {

		val obs = getGraphDataInteractor.buildObservable()
		assertEquals("hello", obs.blockingFirst())
	}
}