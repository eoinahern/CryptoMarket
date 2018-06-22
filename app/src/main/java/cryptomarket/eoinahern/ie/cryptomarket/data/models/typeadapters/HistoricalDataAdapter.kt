package cryptomarket.eoinahern.ie.cryptomarket.data.models.typeadapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import cryptomarket.eoinahern.ie.cryptomarket.data.models.HistoricalData
import cryptomarket.eoinahern.ie.cryptomarket.data.models.TimeInstanceData

/**
 * stupid looking overly verbose class. I wonder is there
 * a way to create adaptors of generate them?
 *
 */

class HistoricalDataAdapter {

	@FromJson
	fun fromJson(jsonReader: JsonReader): HistoricalData {

		jsonReader.isLenient = true

		var list = ArrayList<TimeInstanceData>()

		jsonReader.beginObject()

		jsonReader.nextName()
		val resp = jsonReader.nextString()
		jsonReader.nextName()
		val type = jsonReader.nextInt()
		jsonReader.nextName()
		jsonReader.nextBoolean()
		jsonReader.nextName()
		jsonReader.beginArray()
		while (jsonReader.hasNext()) {

			jsonReader.beginObject()
			jsonReader.nextName()
			val time = jsonReader.nextLong()
			jsonReader.nextName()
			val close = jsonReader.nextDouble().toFloat()
			jsonReader.nextName()
			val high = jsonReader.nextDouble().toFloat()
			jsonReader.nextName()
			val low = jsonReader.nextDouble().toFloat()
			jsonReader.nextName()
			val open = jsonReader.nextDouble().toFloat()
			jsonReader.nextName()
			val volumefrom = jsonReader.nextDouble().toFloat()
			jsonReader.nextName()
			val volumeto = jsonReader.nextDouble()
			jsonReader.endObject()

			list.add(TimeInstanceData(time, close, high,
					low, open, volumefrom, volumeto))
		}

		jsonReader.endArray()
		jsonReader.nextName()
		jsonReader.nextLong()
		jsonReader.nextName()
		jsonReader.nextLong()
		jsonReader.nextName()
		jsonReader.nextBoolean()
		jsonReader.nextName()
		jsonReader.skipValue()

		jsonReader.endObject()

		return HistoricalData(resp, type, list)
	}
}