package cryptomarket.eoinahern.ie.cryptomarket.DI.components

import cryptomarket.eoinahern.ie.cryptomarket.DI.modules.ApplicationModule
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.alerts.AlertsActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
 interface ApplicationComponent {


	fun plus(module : AlertsActivityComponent.AlertsActivityModule) : AlertsActivityComponent
}