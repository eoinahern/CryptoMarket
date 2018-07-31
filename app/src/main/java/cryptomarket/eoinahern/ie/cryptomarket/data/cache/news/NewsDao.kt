package cryptomarket.eoinahern.ie.cryptomarket.data.cache.news

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface NewsDao {

	@Insert
	fun insertNewsList(newsList: List<CryptoNewsItem>)

	@Query("DELETE FROM CryptoNewsItem")
	fun deleteAllNewsData()

	@Query("SELECT * FROM  CryptoNewsItem")
	fun getAllNewsData(): Flowable<List<CryptoNewsItem>>

	@Query("SELECT COUNT(*) FROM CryptoNewsItem")
	fun countRows(): Single<Int>
}