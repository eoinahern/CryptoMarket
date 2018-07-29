package cryptomarket.eoinahern.ie.cryptomarket.data.models

import de.jodamob.kotlin.testrunner.KotlinTestRunner
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(KotlinTestRunner::class)
class CoinMarketCryptoTest {

	private lateinit var crypto1: CoinMarketCrypto
	private lateinit var crypto2: CoinMarketCrypto
	private lateinit var crypto3: CoinMarketCrypto

	@Before
	fun setUp() {

		val map: Map<String, CoinMarketQuotes> = HashMap()

		crypto1 = CoinMarketCrypto(1, "bitcoin", "BTC", "web", 1,
				1, 1, 1, map)
		crypto2 = CoinMarketCrypto(1, "bitcoin", "BTC", "web", 1,
				1, 1, 1, map)
		crypto3 = CoinMarketCrypto(1, "bitcoin", "booo", "web", 1,
				1, 1, 1, map)
	}

	@Test
	fun equals() {
		val res = crypto1.equals(crypto2)
		assertEquals(true, res)

		val result2 = crypto2.equals(crypto3)
		assertEquals(false, result2)
	}
}