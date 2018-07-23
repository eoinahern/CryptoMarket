package cryptomarket.eoinahern.ie.cryptomarket.domain.repository

import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoNewsItem
import io.reactivex.Observable

interface NewsRepository {

	fun getNews(): Observable<List<CryptoNewsItem>>
}