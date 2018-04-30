package cryptomarket.eoinahern.ie.cryptomarket

import android.app.Application
import cryptomarket.eoinahern.ie.cryptomarket.DI.components.ApplicationComponent
import cryptomarket.eoinahern.ie.cryptomarket.DI.modules.ApplicationModule


class MyApp : Application() {

	private lateinit var appComponent: ApplicationComponent

	override fun onCreate() {
		super.onCreate()

		appComponent = DaggerApplicationComponent.builder()
				.applicationModule(ApplicationModule(this)).build()

	}

	companion object {
		fun getAppComponent(): ApplicationComponent = getAppComponent()
	}
}