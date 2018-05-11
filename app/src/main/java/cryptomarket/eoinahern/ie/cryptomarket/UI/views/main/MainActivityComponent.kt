package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import cryptomarket.eoinahern.ie.cryptomarket.DI.components.BaseActivityComponent
import cryptomarket.eoinahern.ie.cryptomarket.DI.modules.BaseActivityModule
import dagger.Module
import dagger.Subcomponent

@Subcomponent(modules = [MainActivityComponent::class])
interface MainActivityComponent : BaseActivityComponent<MainActivity> {

	@Module
	class MainActivityModule(mainActivity : MainActivity) : BaseActivityModule<MainActivity>(mainActivity)

}