package cryptomarket.eoinahern.ie.cryptomarket.domain.base

import io.reactivex.observers.DisposableObserver


open abstract class BaseDisposableObserver<T> : DisposableObserver<T>() {


	override fun onComplete() {
        println("complete!")
	}

}