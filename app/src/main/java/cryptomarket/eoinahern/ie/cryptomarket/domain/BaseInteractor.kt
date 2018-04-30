package cryptomarket.eoinahern.ie.cryptomarket.domain

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


open abstract class BaseInteractor<T> {

	private val disposables: CompositeDisposable = CompositeDisposable()

	abstract fun buildObservable(): Observable<T>

	fun execute(disp: DisposableObserver<T>) {

		disposables.add(buildObservable()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(disp))
	}

	fun execute(disp: Observer<T>) {

		buildObservable()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(disp)

	}

	fun disposeObs() {

		if (disposables.size() != 0)
			disposables.dispose()
	}
}