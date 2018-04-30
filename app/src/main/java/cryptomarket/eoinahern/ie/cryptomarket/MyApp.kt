package cryptomarket.eoinahern.ie.cryptomarket

import android.app.Application
import cryptomarket.eoinahern.ie.cryptomarket.DI.components.ApplicationComponent
import cryptomarket.eoinahern.ie.cryptomarket.DI.components.DaggerApplicationComponent
import cryptomarket.eoinahern.ie.cryptomarket.DI.modules.ApplicationModule

class MyApp : Application() {

	private lateinit var appComponent: ApplicationComponent

	override fun onCreate() {
		super.onCreate()
		initComponent()
	}

	private fun initComponent() {
		appComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
	}

	fun getAppComponent(): ApplicationComponent = appComponent

}