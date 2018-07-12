package cryptomarket.eoinahern.ie.cryptomarket.data.models

import com.squareup.moshi.Json
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.*

data class CurrencyFullPriceDataDisplay(
		@Json(name = toSymbolSyr)
		val TOSYMBOL: String,
		@Json(name = priceStr)
		val PRICE: String,
		@Json(name = changeStr)
		val CHANGEPCT24HOUR: String,
		@Json(name = highDayStr)
		val HIGHDAY: String,
		@Json(name = lowdayStr)
		val LOWDAY: String,
		@Json(name = totalVol24hStr)
		val TOTALVOLUME24HTO: String,
		@Json(name = marketStr)
		val MARKET: String,
		@Json(name = vol24hStr)
		val VOLUME24HOURTO: String,
		@Json(name = marketStr)
		val MKTCAP: String,
		@Json(name = supplyStr)
		val SUPPLY: String)


fun CurrencyFullPriceDataDisplay.isMinus(): Boolean = (CHANGEPCT24HOUR[0] == '-')
