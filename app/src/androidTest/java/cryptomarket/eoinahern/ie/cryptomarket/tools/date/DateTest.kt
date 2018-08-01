package cryptomarket.eoinahern.ie.cryptomarket.tools.date

import com.jakewharton.threetenabp.AndroidThreeTen
import org.junit.Before

import android.support.test.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.threeten.bp.LocalDate

class DateTest {

	private lateinit var dateUtil: DateUtil
	private val dateTimeStr = "2018-07-25T14:19:08.000Z"
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

	@Test
	fun localDateFromStringTest() {
		val localDate = dateUtil.getLocalDateFromString(dateTimeStr)
		Assert.assertEquals("2018-07-25", localDate)
	}

	@Test
	fun olderThanTwoDaysTest() {
		val localDate = dateUtil.getTodaysDateStr()
		var result = dateUtil.checkLargerThanTwoDays(localDate)
		Assert.assertEquals(false, result)

		result = dateUtil.checkLargerThanTwoDays(dateUtil.getLocalDateFromString(dateTimeStr))
		Assert.assertEquals(true, result)
	}
}