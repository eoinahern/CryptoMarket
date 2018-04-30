package cryptomarket.eoinahern.ie.cryptomarket.DI.components

import cryptomarket.eoinahern.ie.cryptomarket.UI.base.BaseActivity


interface BaseActivityComponent<in T : BaseActivity> {
	fun inject(activity : T)
}