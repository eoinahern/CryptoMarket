package cryptomarket.eoinahern.ie.cryptomarket.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@Entity
@JsonClass(generateAdapter = true)
data class CryptoNewsItem(
		val primaryCategory: String,
		val words: String,
		@PrimaryKey
		@Json(name = "_id")
		val id: String,
		val description: String,
		val publishedAt: String,
		val title: String,
		val url: String,
		var thumbnail: String?,
		val sourceDomain: String)