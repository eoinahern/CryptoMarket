package cryptomarket.eoinahern.ie.cryptomarket.UI.util

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import cryptomarket.eoinahern.ie.cryptomarket.R


class LoadingView : FrameLayout {



	constructor(context: Context) : super(context) {
		initiateView(context)
	}

	constructor(context: Context, attrs : AttributeSet) : super(context, attrs) {
		initiateView(context)
	}

	constructor(context: Context, attrs : AttributeSet, defStyleAttr : Int) : super(context, attrs, defStyleAttr) {
		initiateView(context)
	}


	fun initiateView(cont : Context) {

		var v  = inflate(context, R.layout.loading_view_layout, this)



	}

	public fun setState(state : State) {
	}

	enum class State {
		LOADING,
		NETWORK_ERROR,
		NO_CONN_ERROR
	}
}