package cryptomarket.eoinahern.ie.cryptomarket.tools.date

import com.jakewharton.threetenabp.AndroidThreeTen
import org.junit.Before

import android.support.test.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.threeten.bp.LocalDate

class DateTest {

	private lateinit var dateUtil: DateUtil

	private val context = InstrumentationRegistry.getTargetContext().applicationContext

	@Before
	fun setup() {
		AndroidThreeTen.init(context)
		dateUtil = DateUtil()
	}

	@Test
	fun largerThanOneDayTest() {
		val todayDateStr = dateUtil.getTodaysDateStr()
		var largerThanOneday = dateUtil.checkLargerThanOneDay(todayDateStr)
		Assert.assertEquals(false, largerThanOneday)

		val threeDaysPrevious = LocalDate.now().minusDays(3).toString()
		largerThanOneday = dateUtil.checkLargerThanOneDay(threeDaysPrevious)
		Assert.assertEquals(true, largerThanOneday)
	}
}