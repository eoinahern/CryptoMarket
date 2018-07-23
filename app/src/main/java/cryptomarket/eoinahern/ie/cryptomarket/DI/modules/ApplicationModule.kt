package cryptomarket.eoinahern.ie.cryptomarket.DI.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager.getDefaultSharedPreferences
import com.google.gson.Gson
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.NewsCache
import cryptomarket.eoinahern.ie.cryptomarket.data.cache.NewsCacheIml
import cryptomarket.eoinahern.ie.cryptomarket.tools.date.DateUtil
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
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
	fun getGson(): Gson = Gson()


	@Singleton
	@Provides
	fun getDateUtil() = DateUtil()

	@Singleton
	@Provides
	fun getNewsCache(): NewsCache {
		return NewsCacheIml()
	}

}