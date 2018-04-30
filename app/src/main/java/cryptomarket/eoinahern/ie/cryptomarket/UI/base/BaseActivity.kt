package cryptomarket.eoinahern.ie.cryptomarket.UI.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

open abstract class BaseActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)


		//inject all injected dependencies into child if required
		inject()
	}

	abstract fun inject()
}
