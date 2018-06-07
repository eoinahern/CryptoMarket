package cryptomarket.eoinahern.ie.cryptomarket.domain.base

import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class BaseSubscriber<T> : Observer<T> {

	override fun onSubscribe(d: Disposable) {
		println("subscribed")
	}

	override fun onComplete() {
		Log.d("complete", "complete!")

	}
}