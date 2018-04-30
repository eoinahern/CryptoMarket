package cryptomarket.eoinahern.ie.cryptomarket.UI.base


public interface Presenter<in V : MvpView> {

	fun attachView(view : V)

	fun detachView(view : V)
}