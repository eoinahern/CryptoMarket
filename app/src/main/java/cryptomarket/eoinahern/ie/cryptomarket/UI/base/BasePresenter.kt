package cryptomarket.eoinahern.ie.cryptomarket.UI.base


open class BasePresenter<V : MvpView> : Presenter<V> {

	var myView : V? = null

	override fun attachView(view: V) {
       myView = view
	}

	override fun detachView(view: V) {

		myView?.let{
            myView = null
		}
	}

	override fun getView() : V? = myView
}