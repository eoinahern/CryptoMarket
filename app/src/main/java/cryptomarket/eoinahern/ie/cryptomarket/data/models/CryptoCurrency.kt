package cryptomarket.eoinahern.ie.cryptomarket.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import paperparcel.PaperParcel
import paperparcel.PaperParcelable


@Entity
@PaperParcel
@JsonClass(generateAdapter = true)
data class CryptoCurrency(
		@Json(name = "Id")
		val Id: String,
		@Json(name = "Url")
		val Url: String,
		@Json(name = "ImageUrl")
		val ImageUrl: String = "not found",
		@Json(name = "Name")
		val Name: String,
		@PrimaryKey
		@Json(name = "Symbol")
		val Symbol: String,
		@Json(name = "CoinName")
		val CoinName: String,
		@Json(name = "FullName")
		val FullName: String,
		@Json(name = "SortOrder")
		val SortOrder: String) : Comparable<CryptoCurrency>, PaperParcelable {

	override fun compareTo(other: CryptoCurrency): Int {
		return SortOrder.toInt() - other.SortOrder.toInt()

	}

	companion object {
		@JvmField
		val CREATOR = PaperParcelCryptoCurrency.CREATOR
	}
}
