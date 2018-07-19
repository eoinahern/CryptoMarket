package cryptomarket.eoinahern.ie.cryptomarket.UI.views.news

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.DI.components.BaseActivityComponent
import cryptomarket.eoinahern.ie.cryptomarket.DI.modules.BaseActivityModule
import dagger.Module
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [NewsActivityComponent.NewsActivityModule::class])
interface NewsActivityComponent : BaseActivityComponent<NewsActivity> {

	@Module
	class NewsActivityModule constructor(activity: NewsActivity) : BaseActivityModule<NewsActivity>(activity)
}