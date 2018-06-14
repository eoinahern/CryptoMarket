package cryptomarket.eoinahern.ie.cryptomarket.UI.util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import cryptomarket.eoinahern.ie.cryptomarket.R


class LoadingView : FrameLayout {

	private lateinit var progressLayout: LinearLayout
	private lateinit var errorView: ImageView

	constructor(context: Context) : super(context) {
		initiateView(context)
	}

	constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
		initiateView(context)
	}

	constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
		initiateView(context)
	}


	private fun initiateView(cont: Context) {

		var v = inflate(context, R.layout.loading_view_layout, this)

		progressLayout = v.findViewById(R.id.loading)
		errorView = v.findViewById(R.id.error_img)
	}

	public fun setState(state: State) {

		when (state) {

			State.LOADING -> {
				progressLayout.visibility = View.VISIBLE
				errorView.visibility = View.INVISIBLE
			}
			State.NETWORK_ERROR -> {
				progressLayout.visibility = View.INVISIBLE
				errorView.visibility = View.VISIBLE
			}
		}
	}

	public fun hide() {
		visibility = View.GONE
	}

	enum class State {
		LOADING,
		NETWORK_ERROR,
		NO_CONN_ERROR
	}
}