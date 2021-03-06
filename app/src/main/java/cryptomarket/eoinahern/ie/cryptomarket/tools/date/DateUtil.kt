package cryptomarket.eoinahern.ie.cryptomarket.tools.date

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class DateUtil {

	private val dateFormatter: DateTimeFormatter = DateTimeFormatter
			.ofPattern("dd/MMM/yy HH:mm a", Locale.getDefault())


	fun getLocalDateTime(linuxTime: Long): String? {

		val localtime = LocalDateTime.ofInstant(Instant.ofEpochSecond(linuxTime),
				ZoneId.systemDefault())

		return localtime?.format(dateFormatter)
	}

	fun checkLargerThanOneDay(date: String): Boolean {

		if (date.isEmpty())
			return false

		val daySaved = LocalDate.parse(date)
		val currentDate = LocalDate.now()

		if (daySaved.isBefore(currentDate))
			return true

		return false
	}


	fun checkLargerThanTwoDays(dateStr: String): Boolean {

		if (dateStr.isEmpty())
			return false

		val savedDate = LocalDate.parse(dateStr)
		val current = LocalDate.now()

		if (savedDate.plusDays(1).isBefore(current))
			return true

		return false
	}


	fun getTodaysDateStr() = LocalDate.now().toString()

	fun getLocalDateFromString(inputDateStr: String): String {
		val instant = Instant.parse(inputDateStr)
		return instant.atZone(ZoneId.systemDefault()).toLocalDate().toString()
	}
}