package cryptomarket.eoinahern.ie.cryptomarket.DI.modules

import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.api.MinApiCryptoCompare
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

	@Singleton
	@Provides
    fun getCryptoApi() : CryptoApi {

		return  Retrofit.Builder()
				.baseUrl("https://min-api.cryptocompare.com/data/")
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build().create(CryptoApi::class.java)
	}
}