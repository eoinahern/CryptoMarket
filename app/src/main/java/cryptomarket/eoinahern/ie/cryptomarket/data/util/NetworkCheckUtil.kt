package cryptomarket.eoinahern.ie.cryptomarket.data.util

import android.content.Context
import android.net.ConnectivityManager


class NetworkCheckUtil {
	companion object {
		fun isConnected(context: Context): Boolean {
			val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
			var netInfo = connManager.activeNetworkInfo
			return (netInfo != null && netInfo.isConnected)
		}
	}
}