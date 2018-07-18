package cryptomarket.eoinahern.ie.cryptomarket.domain.base

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


open abstract class BaseInteractor<T> {

	private val disposables: CompositeDisposable = CompositeDisposable()

	abstract fun buildObservable(): Observable<T>

	fun execute(disp: BaseDisposableObserver<T>) {
		disposeObs()

		disposables.add(buildObservable()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(disp))
	}

	fun execute(disp: BaseSubscriber<T>) {

		buildObservable()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(disp)

	}

	fun addDisposables(d : Disposable) {
		disposables.add(d)
	}

	fun disposeObs() {

		if (disposables.size() != 0 && !disposables.isDisposed)
			disposables.dispose()
	}
}