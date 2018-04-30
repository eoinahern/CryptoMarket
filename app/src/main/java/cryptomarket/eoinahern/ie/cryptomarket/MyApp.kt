package cryptomarket.eoinahern.ie.cryptomarket

import android.app.Application
import cryptomarket.eoinahern.ie.cryptomarket.DI.components.ApplicationComponent


class MyApp : Application() {

	private lateinit var appComponent : ApplicationComponent

	override fun  onCreate() {
		super.onCreate()


	}



	companion object {
		fun getAppComponent() : ApplicationComponent = getAppComponent()
	}
}