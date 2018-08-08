package cryptomarket.eoinahern.ie.cryptomarket.domain.main

import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable

class SaveFavouritesDataInteractor : BaseInteractor<Unit>() {




	override fun buildObservable(): Observable<Unit> {
		return Observable.just(Unit)
	}


}