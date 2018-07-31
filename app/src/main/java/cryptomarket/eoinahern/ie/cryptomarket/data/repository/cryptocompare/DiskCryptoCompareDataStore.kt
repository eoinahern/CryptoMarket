package cryptomarket.eoinahern.ie.cryptomarket.data.repository.cryptocompare

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import io.reactivex.Observable

class DiskCryptoCompareDataStore : CryptoCompareDataStore {



	override fun getCryptoComparedata(): Observable<Map<String, CryptoCurrency>> {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}


}