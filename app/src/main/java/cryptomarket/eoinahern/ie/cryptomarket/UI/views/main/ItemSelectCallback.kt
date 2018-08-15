package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

interface ItemSelectCallback {

	fun cryptoSelected(position: Int)

	fun favouritesChecked(position: Int, isChecked: Boolean)
}