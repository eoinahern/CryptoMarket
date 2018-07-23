package cryptomarket.eoinahern.ie.cryptomarket.data.cache

import de.jodamob.kotlin.testrunner.KotlinTestRunner
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@RunWith(KotlinTestRunner::class)
class NewsCacheImlTest {

	@Mock
	private lateinit var newsCacheImp: NewsCacheIml

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		newsCacheImp = NewsCacheIml()
	}

	@Test
	fun testIsEmpty() {

	}


	@Test
	fun saveData() {

	}

	@Test
	fun hasData() {
		assertEquals(false, newsCacheImp.hasData())
	}


}