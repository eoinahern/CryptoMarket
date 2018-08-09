package cryptomarket.eoinahern.ie.cryptomarket.data.cache.sharedprefs

import android.content.SharedPreferences


class SharedPrefsHelper constructor(private val sharedPrefs: SharedPreferences,
									private val sharePrefsEdit: SharedPreferences.Editor) {


	fun getString(id: String, default: String = ""): String {
		return sharedPrefs.getString(id, default)
	}

	fun saveString(id: String, data: String) {
		sharePrefsEdit.putString(id, data).apply()
	}

}