package cryptomarket.eoinahern.ie.cryptomarket.UI.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

open abstract class BaseActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
        setContentView(getLayoutView())

		//inject all injected dependencies into child if required
		inject()
	}

	abstract fun inject()
	abstract fun getLayoutView() :Int
}
