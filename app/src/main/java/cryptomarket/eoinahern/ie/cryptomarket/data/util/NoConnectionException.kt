package cryptomarket.eoinahern.ie.cryptomarket.data.util

import java.io.IOException

class NoConnectionException : IOException() {
	override fun getLocalizedMessage(): String? = "no connection"
}