package cryptomarket.eoinahern.ie.cryptomarket.data.models.typeadapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyFullPriceDataDisplay
import cryptomarket.eoinahern.ie.cryptomarket.data.models.FullPriceWrapperInternalDisplay

class FullPriceWrapperInternalDisplayAdapter {

	val toSymbolSyr: String = "TOSYMBOL"
	val fromSymbolStr = "FROMSYMBOL"
	val marketStr = "MARKET"

	@FromJson
	fun fromJson(jsonReader: JsonReader): FullPriceWrapperInternalDisplay {

		var itemMap = HashMap<String, CurrencyFullPriceDataDisplay>()

		jsonReader.isLenient = true

		jsonReader.beginObject()
		while (jsonReader.hasNext()) {

			val currencyName = jsonReader.nextName()
			val currency = jsonReader.readJsonValue() as Map<String, String>
			val tosymbol = currency[toSymbolSyr] ?: ""
			val fromsymbol = currency[fromSymbolStr] ?: ""
			val market = currency[marketStr] ?: ""

			val curr = CurrencyFullPriceDataDisplay(fromsymbol, tosymbol, market)
			itemMap[currencyName] = curr
		}

		jsonReader.endObject()

		return FullPriceWrapperInternalDisplay(itemMap)
	}
}