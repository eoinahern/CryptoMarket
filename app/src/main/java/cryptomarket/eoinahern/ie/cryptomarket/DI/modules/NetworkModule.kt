package cryptomarket.eoinahern.ie.cryptomarket.DI.modules

import android.content.Context
import cryptomarket.eoinahern.ie.cryptomarket.UI.util.compareApiEndPoint
import cryptomarket.eoinahern.ie.cryptomarket.data.api.ConnectionCheckInterceptor
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.api.MinApiCryptoCompare
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

	@Singleton
	@Provides
	fun getClient(context : Context) : OkHttpClient {

		val client = OkHttpClient().newBuilder()
				.addInterceptor(ConnectionCheckInterceptor(context))
				.build()

		return client
	}

	@Singleton
	@Provides
	fun getCryptoApi(client : OkHttpClient): CryptoApi {

		return Retrofit.Builder()
				.baseUrl(compareApiEndPoint)
				.client(client)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build().create(CryptoApi::class.java)
	}
}