package cryptomarket.eoinahern.ie.cryptomarket.DI.modules

import android.content.Context
import com.google.gson.Gson
import cryptomarket.eoinahern.ie.cryptomarket.data.CryptoApi
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
    fun getCryptoApi(endpoint : HttpUrl, gson : Gson) : CryptoApi{

		return  Retrofit.Builder()
				.baseUrl(endpoint)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build().create(CryptoApi::class.java)
	}
}