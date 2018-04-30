package cryptomarket.eoinahern.ie.cryptomarket.UI.base


 interface Presenter<in V : MvpView> {

	fun attachView(view : V)

	fun detachView(view : V)
}