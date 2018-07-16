package cryptomarket.eoinahern.ie.cryptomarket.data.models.typeadapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CurrencyFullPriceDataDisplay
import cryptomarket.eoinahern.ie.cryptomarket.data.models.FullPriceWrapperInternalDisplay
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.*

class FullPriceWrapperInternalDisplayAdapter {


	@FromJson
	fun fromJson(jsonReader: JsonReader): FullPriceWrapperInternalDisplay {

		var itemMap = HashMap<String, CurrencyFullPriceDataDisplay>()

		jsonReader.isLenient = true

		jsonReader.beginObject()
		while (jsonReader.hasNext()) {

			val currencyName = jsonReader.nextName()
			val currency = jsonReader.readJsonValue() as Map<String, String>
			val tosymbol = currency[toSymbolSyr] ?: ""
			val price = currency[priceStr] ?: "Unknown"
			val changePct = currency[changeStr] ?: ""
			val highDay = currency[highDayStr] ?: notAvailableStr
			val lowDay = currency[lowdayStr] ?: notAvailableStr
			val totalVol24h = currency[totalVol24hStr] ?: notAvailableStr
			val market = currency[marketStr] ?: notAvailableStr
			val vol24h = currency[vol24hStr] ?: notAvailableStr
			val marketCap = currency[marketCapStr] ?: notAvailableStr
			val supply = currency[supplyStr] ?: notAvailableStr


			val curr = CurrencyFullPriceDataDisplay(tosymbol, price, changePct, highDay,
					lowDay, totalVol24h, market, vol24h, marketCap, supply)
			itemMap[currencyName] = curr
		}

		jsonReader.endObject()

		return FullPriceWrapperInternalDisplay(itemMap)
	}
}