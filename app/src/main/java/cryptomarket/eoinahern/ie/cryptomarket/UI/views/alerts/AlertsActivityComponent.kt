package cryptomarket.eoinahern.ie.cryptomarket.UI.views.alerts

import cryptomarket.eoinahern.ie.cryptomarket.DI.components.BaseActivityComponent
import cryptomarket.eoinahern.ie.cryptomarket.DI.modules.BaseActivityModule
import dagger.Module
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(AlertsActivityComponent.AlertsActivityModule::class))
interface AlertsActivityComponent : BaseActivityComponent<AlertsActivity>{

	@Module
    class AlertsActivityModule(activity : AlertsActivity) : BaseActivityModule<AlertsActivity>(activity)
}