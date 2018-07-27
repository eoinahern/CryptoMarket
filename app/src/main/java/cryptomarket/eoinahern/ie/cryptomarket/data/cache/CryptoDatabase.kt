package cryptomarket.eoinahern.ie.cryptomarket.data.cache

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.news.NewsDao
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem

@Database(entities = [CryptoNewsItem::class], version = 1, exportSchema = false)
abstract class CryptoDatabase : RoomDatabase() {
	abstract fun newsDao(): NewsDao
}