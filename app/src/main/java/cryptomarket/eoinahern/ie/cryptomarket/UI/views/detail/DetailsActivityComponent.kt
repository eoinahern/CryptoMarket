package cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.DI.components.BaseActivityComponent
import cryptomarket.eoinahern.ie.cryptomarket.DI.modules.BaseActivityModule
import dagger.Module
import dagger.Subcomponent


@PerScreen
@Subcomponent(modules = [DetailsActivityComponent.DetailsActivityCompnentModule::class])
interface DetailsActivityComponent : BaseActivityComponent<DetailsActivity> {

	@Module
	class DetailsActivityCompnentModule(detailsActivity: DetailsActivity) :
			BaseActivityModule<DetailsActivity>(detailsActivity)
}