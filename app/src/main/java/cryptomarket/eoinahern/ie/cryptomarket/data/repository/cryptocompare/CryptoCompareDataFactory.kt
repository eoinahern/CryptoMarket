package cryptomarket.eoinahern.ie.cryptomarket.data.repository.cryptocompare

import javax.inject.Inject


class CryptoCompareDataFactory @Inject constructor(private val apiCryptoCompareDataStore: ApiCryptoCompareDataStore,
												   private val diskCryptoCompareDataStore: DiskCryptoCompareDataStore) {


	fun getDataStore(): CryptoCompareDataStore {
		return apiCryptoCompareDataStore
	}
}