package cryptomarket.eoinahern.ie.cryptomarket.DI.modules


import android.content.Context
import com.squareup.moshi.Moshi
import cryptomarket.eoinahern.ie.cryptomarket.data.api.*
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.compareApiEndPoint
import cryptomarket.eoinahern.ie.cryptomarket.data.models.typeadapters.FullPriceWrapperInternalDisplayAdapter
import cryptomarket.eoinahern.ie.cryptomarket.data.models.typeadapters.HistoricalDataAdapter
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.coinMarketCapAPI
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.compareApiEndPointOld
import cryptomarket.eoinahern.ie.cryptomarket.tools.consts.cryptoNewsAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named


@Module
class NetworkModule {

	@Provides
	@Singleton
	fun getMoshi(): Moshi {

		return Moshi.Builder().add(FullPriceWrapperInternalDisplayAdapter())
				.add(HistoricalDataAdapter())
				.build()
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
	fun getCryptoApi(moshi: Moshi, client: OkHttpClient): CryptoApi {

		return Retrofit.Builder()
				.baseUrl(compareApiEndPoint)
				.client(client)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(MoshiConverterFactory.create(moshi))
				.build().create(CryptoApi::class.java)
	}

	@Singleton
	@Provides
	fun getCryptoApiOld(moshi: Moshi, client: OkHttpClient): CryptoApiOld {
		return Retrofit.Builder()
				.baseUrl(compareApiEndPointOld)
				.client(client)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(MoshiConverterFactory.create(moshi))
				.build().create(CryptoApiOld::class.java)
	}

	@Singleton
	@Provides
	fun getCoinMarketCap(moshi: Moshi, client: OkHttpClient): CoinMarketCapApi {

		return Retrofit.Builder()
				.baseUrl(coinMarketCapAPI)
				.client(client)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(MoshiConverterFactory.create(moshi))
				.build().create(CoinMarketCapApi::class.java)
	}


	@Singleton
	@Provides
	fun getCryptoNews(moshi: Moshi, client: OkHttpClient): CryptoNewsApi {
		return Retrofit.Builder()
				.baseUrl(cryptoNewsAPI)
				.client(client)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(MoshiConverterFactory.create(moshi))
				.build().create(CryptoNewsApi::class.java)
	}

	@Singleton
	@Provides
	@Named("newsKey")
	fun getKeyNewsKey() = "fae90d4015b69142983151036d604763"

}