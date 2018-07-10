package cryptomarket.eoinahern.ie.cryptomarket.domain.details

import cryptomarket.eoinahern.ie.cryptomarket.DI.annotation.PerScreen
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApiOld
import cryptomarket.eoinahern.ie.cryptomarket.data.models.GeneralCoinInfo
import cryptomarket.eoinahern.ie.cryptomarket.domain.base.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

@PerScreen
class GetCryptoInfoInteractor @Inject constructor(private val cryptoApi: CryptoApiOld) : BaseInteractor<GeneralCoinInfo>() {

	private lateinit var id: String

	fun setID(id: String): GetCryptoInfoInteractor {
		this.id = id
		return this
	}

	override fun buildObservable(): Observable<GeneralCoinInfo> {
		return cryptoApi.getCoinSnapShotById(id)
				.map { snapShot -> snapShot.Data.General }
	}
}
