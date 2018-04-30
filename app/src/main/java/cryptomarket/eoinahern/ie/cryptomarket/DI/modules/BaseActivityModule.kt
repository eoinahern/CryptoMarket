package cryptomarket.eoinahern.ie.cryptomarket.DI.modules

import android.app.Activity
import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BaseActivity
import dagger.Module
import dagger.Provides


@Module
abstract class BaseActivityModule<out T : BaseActivity> constructor(private var activity : T)  {

	@Provides
	@PerScreen
	fun activityT() : T = activity

	@Provides
	@PerScreen
	fun activity() : Activity = activity
}