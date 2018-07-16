package cryptomarket.eoinahern.ie.cryptomarket.tools.date

import org.threeten.bp.Instant
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
}