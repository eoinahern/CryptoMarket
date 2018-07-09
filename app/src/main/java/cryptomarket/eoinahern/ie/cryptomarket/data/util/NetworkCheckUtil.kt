package cryptomarket.eoinahern.ie.cryptomarket.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


class NetworkCheckUtil {
	companion object {
		fun isConnected(context: Context): Boolean {
			val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
			var netInfo: NetworkInfo? = connManager.activeNetworkInfo ?: return false
			return netInfo?.isConnectedOrConnecting ?: false
		}
	}
}
