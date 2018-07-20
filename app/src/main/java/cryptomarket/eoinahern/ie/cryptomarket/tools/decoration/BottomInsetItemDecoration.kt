package cryptomarket.eoinahern.ie.cryptomarket.tools.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.TypedValue


class BottomInsetItemDecoration : RecyclerView.ItemDecoration {

	private val paint: Paint = Paint()
	private var inset: Float = 10f

	constructor(context: Context, color: Int, height: Float, inset: Float) {
		paint.color = ContextCompat.getColor(context, color)
		val thickness: Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				height, context.resources.displayMetrics)
		this.inset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, inset,
				context.resources.displayMetrics)
		paint.strokeWidth = thickness
	}

	/*override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
		outRect?.set(0, 0, 0, paint.strokeWidth.toInt())
	}*/

	/*override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
		val inset = 10f

		for (i in 0 until parent.childCount - 1) {
			val v = parent.getChildAt(i)
			c.drawLine(v.left.toFloat() + inset, v.bottom - paint.strokeWidth,
					v.right.toFloat() - inset, (v.bottom - paint.strokeWidth), paint)
		}
	}*/

	override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
		for (i in 0 until parent.childCount - 1) {
			val v = parent.getChildAt(i)
			c.drawLine(v.left.toFloat() + inset, v.bottom.toFloat(),
					v.right.toFloat() - inset, (v.bottom.toFloat()), paint)
		}
	}
}