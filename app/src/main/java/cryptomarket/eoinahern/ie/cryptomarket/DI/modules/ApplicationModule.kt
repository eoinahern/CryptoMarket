package cryptomarket.eoinahern.ie.cryptomarket.DI.modules

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager.getDefaultSharedPreferences
import com.google.gson.Gson
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.CryptoDatabase
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.cryptocompare.CryptoCompareCache
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.cryptocompare.CryptoCompareCacheImp
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.news.NewsCache
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.news.NewsCacheIml
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.sharedprefs.SharedPrefsHelper
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.DB_NAME
import cryptomarket.eoinahern.ie.cryptomarket.tools.date.DateUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule constructor(var myApp: MyApp) {

	@Singleton
	@Provides
	fun getContext(): Context = myApp

	@Singleton
	@Provides
	fun getSharedPrefs(cont: Context): SharedPreferences = getDefaultSharedPreferences(cont)

	@Singleton
	@Provides
	fun getPrefsEditor(sharedPrefs: SharedPreferences): SharedPreferences.Editor = sharedPrefs.edit()

	@Singleton
	@Provides
	fun getSharedPrefsHelper(sharedPrefs: SharedPreferences, sharePrefsEdit: SharedPreferences.Editor): SharedPrefsHelper {
		return SharedPrefsHelper(sharedPrefs, sharePrefsEdit)
	}

	@Singleton
	@Provides
	fun getDateUtil() = DateUtil()

	@Singleton
	@Provides
	fun getDB(context: Context): CryptoDatabase {
		return Room.databaseBuilder(context, CryptoDatabase::class.java, DB_NAME)
				.fallbackToDestructiveMigration()
				.build()
	}

	@Singleton
	@Provides
	fun getNewsCache(cryptoDatabase: CryptoDatabase): NewsCache {
		return NewsCacheIml(cryptoDatabase)
	}

	@Singleton
	@Provides
	fun getCryptoCompareCache(cryptoDatabase: CryptoDatabase): CryptoCompareCache {
		return CryptoCompareCacheImp(cryptoDatabase)
	}

}