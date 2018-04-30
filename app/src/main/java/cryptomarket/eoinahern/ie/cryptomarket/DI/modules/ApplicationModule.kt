package cryptomarket.eoinahern.ie.cryptomarket.DI.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager.getDefaultSharedPreferences
import com.google.gson.Gson
import cryptomarket.eoinahern.ie.cryptomarket.MyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule constructor(var myApp : MyApp) {

	@Singleton
	@Provides
	fun getContext() : Context =  myApp

	@Singleton
	@Provides
    fun getSharedPrefs(cont : Context) : SharedPreferences = getDefaultSharedPreferences(cont)

	@Singleton
	@Provides
	fun getGson() : Gson = Gson()
}