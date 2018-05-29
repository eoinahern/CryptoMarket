package cryptomarket.eoinahern.ie.cryptomarket.data.api

import android.content.Context
import cryptomarket.eoinahern.ie.cryptomarket.data.util.NetworkCheckUtil
import cryptomarket.eoinahern.ie.cryptomarket.data.util.NoConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectionCheckInterceptor(private val con: Context) : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {

		if (NetworkCheckUtil.isConnected(con))
			throw NoConnectionException()


		val builder = chain.request().newBuilder()
		return chain.proceed(builder.build())
	}


}