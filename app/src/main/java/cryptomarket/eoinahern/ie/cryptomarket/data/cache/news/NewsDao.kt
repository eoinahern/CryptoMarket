package cryptomarket.eoinahern.ie.cryptomarket.data.cache

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem


@Dao
interface NewsDao {

	@Insert
	fun insertNewsList(newsList: List<CryptoNewsItem>)

	@Delete
	fun deleteAllNewsData()

	@Query("SELECT * FROM  CryptoNewsItem")
	fun getAllNewsData(): List<CryptoNewsItem>

	@Query("SELECT COUNT(*) FROM CryptoNewsItem")
	fun countRows(): Int
}