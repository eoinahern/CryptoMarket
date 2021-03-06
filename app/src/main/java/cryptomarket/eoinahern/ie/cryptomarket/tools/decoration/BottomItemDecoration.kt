package cryptomarket.eoinahern.ie.cryptomarket.tools.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View

class BottomItemDecoration : RecyclerView.ItemDecoration {

	private var paint: Paint = Paint()

	constructor(context: Context, color: Int, height: Float) {
		paint.color = ContextCompat.getColor(context, color)

		//dp to pixel conversion
		val thickness: Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, context.resources.displayMetrics)
		paint.strokeWidth = thickness
	}

	override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

		val params: RecyclerView.LayoutParams = view.layoutParams as RecyclerView.LayoutParams
		val pos: Int = params.viewAdapterPosition
		var count = state.itemCount

		val intStroke = paint.strokeWidth.toInt()


		if (pos == 0) {
			outRect.set(intStroke, intStroke, intStroke, intStroke)
		} else if (pos < count) {
			outRect.set(intStroke, 0, intStroke, intStroke)
		}
	}

	override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

		val offset: Int = (paint.strokeWidth / 2).toInt()

		for (i in 0 until parent.childCount) {
			val v = parent.getChildAt(i)
			val params = v.layoutParams as RecyclerView.LayoutParams

			val pos = params.viewAdapterPosition
			var itemCount = state.itemCount

			if (pos == 0) {

				c.drawLine((v.left - paint.strokeWidth), (v.bottom + offset).toFloat(), (v.right + paint.strokeWidth), (v.bottom + offset).toFloat(), paint)
				c.drawLine((v.right + offset).toFloat(), v.top.toFloat(), (v.right + offset).toFloat(), v.bottom.toFloat(), paint)
				c.drawLine((v.left - offset).toFloat(), v.top.toFloat(), (v.left - offset).toFloat(), v.bottom.toFloat(), paint)
				c.drawLine((v.left - paint.strokeWidth), (v.top - offset).toFloat(), (v.right + paint.strokeWidth), (v.top - offset).toFloat(), paint)

			} else if (pos < itemCount) {

				c.drawLine((v.left - paint.strokeWidth), (v.bottom + offset).toFloat(), (v.right + paint.strokeWidth), (v.bottom + offset).toFloat(), paint)
				c.drawLine((v.right + offset).toFloat(), v.top.toFloat(), (v.right + offset).toFloat(), v.bottom.toFloat(), paint)
				c.drawLine((v.left - offset).toFloat(), v.top.toFloat(), (v.left - offset).toFloat(), v.bottom.toFloat(), paint)
			}
		}
	}
}