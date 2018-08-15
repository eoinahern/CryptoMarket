package cryptomarket.eoinahern.ie.cryptomarket.UI.views.favourites

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.DI.components.BaseActivityComponent
import cryptomarket.eoinahern.ie.cryptomarket.DI.modules.BaseActivityModule
import dagger.Module
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [FavouritesActivityComponent.FavouritesActivityModule::class])
interface FavouritesActivityComponent : BaseActivityComponent<FavouritesActivity> {

	@Module
	class FavouritesActivityModule constructor(activity: FavouritesActivity) : BaseActivityModule<FavouritesActivity>(activity)
}