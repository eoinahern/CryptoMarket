package cryptomarket.eoinahern.ie.cryptomarket.DI.modules

import android.content.Context
import cryptomarket.eoinahern.ie.cryptomarket.data.CryptoApi
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit




@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named("endpoint")
	fun getEndpoint() : HttpUrl? {
		return HttpUrl.parse("http://www.cryptocompare.com/")
	}

	@Singleton
	@Provides
    fun getCryptoApi(endpoint : HttpUrl) : CryptoApi{

		return  Retrofit.Builder()
				.baseUrl(endpoint)
				.addConverterFactory(GsonConverterFactory.create())
				.build().create(CryptoApi::class.java)
	}
}