package cryptomarket.eoinahern.ie.cryptomarket.data.cache.cryptocompare

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface CryptoCompareDao {

	@Insert
	fun insertCurrency(list: List<CryptoCurrency>)

	@Query("SELECT * FROM CryptoCurrency")
	fun getAll(): Flowable<List<CryptoCurrency>>

	@Query("DELETE FROM CryptoCurrency")
	fun deleteAll()

	@Query("SELECT COUNT(*) FROM CryptoCurrency")
	fun countRows(): Single<Int>
}