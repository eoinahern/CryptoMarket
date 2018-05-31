package cryptomarket.eoinahern.ie.cryptomarket.DI.modules

import android.content.Context
import com.squareup.moshi.Moshi
import cryptomarket.eoinahern.ie.cryptomarket.UI.util.compareApiEndPoint
import cryptomarket.eoinahern.ie.cryptomarket.data.api.ConnectionCheckInterceptor
import cryptomarket.eoinahern.ie.cryptomarket.data.api.CryptoApi
import cryptomarket.eoinahern.ie.cryptomarket.data.models.FullPriceWrapper
import cryptomarket.eoinahern.ie.cryptomarket.data.models.typeadapters.FullPriceWrapperInternalDisplayAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
class NetworkModule {

	@Provides
	@Singleton
	fun getMoshi(): Moshi {
		return Moshi.Builder().add(FullPriceWrapperInternalDisplayAdapter()).build()

	}

	@Singleton
	@Provides
	fun getClient(context: Context): OkHttpClient {

		val client = OkHttpClient().newBuilder()
				.addInterceptor(ConnectionCheckInterceptor(context))
				.build()

		return client
	}

	@Singleton
	@Provides
	fun getCryptoApi(moshi: Moshi): CryptoApi {

		return Retrofit.Builder()
				.baseUrl(compareApiEndPoint)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(MoshiConverterFactory.create(moshi))
				.build().create(CryptoApi::class.java)
	}
}