package cryptomarket.eoinahern.ie.cryptomarket.UI.base


interface Presenter<V : MvpView> {

	fun attachView(view: V)

	fun detachView(view: V)

	fun getView(): V?
}