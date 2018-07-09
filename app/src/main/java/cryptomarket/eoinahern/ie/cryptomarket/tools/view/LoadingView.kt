package cryptomarket.eoinahern.ie.cryptomarket.tools.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import cryptomarket.eoinahern.ie.cryptomarket.R


class LoadingView : FrameLayout {

	private lateinit var progressLayout: LinearLayout
	//private lateinit var= errorView: ImageView
	private lateinit var errorTxt: TextView

	constructor(context: Context) : super(context) {
		initiateView()
	}

	constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
		initiateView()
	}

	constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
		initiateView()
	}


	private fun initiateView() {

		var v = inflate(context, R.layout.loading_view_layout, this)

		progressLayout = v.findViewById(R.id.loading)
		//errorView = v.findViewById(R.id.error_img)
		errorTxt = v.findViewById(R.id.error_txt)

	}

	fun setState(state: State) {

		when (state) {

			State.LOADING -> {
				progressLayout.visibility = View.VISIBLE
				errorTxt.visibility = View.INVISIBLE
			}
			State.NETWORK_ERROR -> {
				progressLayout.visibility = View.INVISIBLE
				errorTxt.visibility = View.VISIBLE
			}
			State.OTHER_ERROR -> {
				progressLayout.visibility = View.INVISIBLE
				errorTxt.visibility = View.VISIBLE
				errorTxt.text = context.getString(R.string.data_loading_error)
			}
		}
	}

	fun hide() {
		visibility = View.GONE
	}

	enum class State {
		LOADING,
		NETWORK_ERROR,
		OTHER_ERROR
	}
}