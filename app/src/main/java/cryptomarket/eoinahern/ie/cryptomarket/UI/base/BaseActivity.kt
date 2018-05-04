package cryptomarket.eoinahern.ie.cryptomarket.UI.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

open abstract class BaseActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		if (getLayoutView() != 0 ) {
			setContentView(getLayoutView())
		}

		inject()
	}

	abstract fun inject()
	abstract fun getLayoutView() :Int
}
