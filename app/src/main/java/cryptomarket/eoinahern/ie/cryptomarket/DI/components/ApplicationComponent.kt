package cryptomarket.eoinahern.ie.cryptomarket.DI.components

import cryptomarket.eoinahern.ie.cryptomarket.DI.modules.ApplicationModule
import cryptomarket.eoinahern.ie.cryptomarket.DI.modules.NetworkModule
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.alerts.AlertsActivityComponent
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.detail.DetailsActivityComponent
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.favourites.FavouritesActivityComponent
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.main.MainActivityComponent
import cryptomarket.eoinahern.ie.cryptomarket.UI.views.news.NewsActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

	fun plus(module: AlertsActivityComponent.AlertsActivityModule): AlertsActivityComponent

	fun plus(module: MainActivityComponent.MainActivityModule): MainActivityComponent

	fun plus(module: DetailsActivityComponent.DetailsActivityCompnentModule): DetailsActivityComponent

	fun plus(module: NewsActivityComponent.NewsActivityModule): NewsActivityComponent

	fun plus(module: FavouritesActivityComponent.FavouritesActivityModule): FavouritesActivityComponent

}