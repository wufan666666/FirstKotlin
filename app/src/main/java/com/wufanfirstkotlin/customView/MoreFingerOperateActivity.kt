package com.wufanfirstkotlin.customView

import android.graphics.Matrix
import android.graphics.PointF
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.wufanfirstkotlin.BaseActivity
import com.wufanfirstkotlin.R

/**
 * @author : wf
 * @date : 2022年03月15日 10:34
 */
class MoreFingerOperateActivity : BaseActivity(), View.OnTouchListener {
    private var img_test: ImageView? = null

    // 縮放控制
    private val matrix = Matrix()
    private val savedMatrix = Matrix()
    private var mode = NONE

    // 定义第一个按下的点，两只接触点的重点，以及出事的两指按下的距离：
    private val startPoint = PointF()
    private var midPoint = PointF()
    private var oriDis = 1f

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_finger_operation)
        img_test = findViewById<View>(R.id.imageView) as ImageView
        img_test!!.setOnTouchListener(this)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val view = v as ImageView
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                matrix.set(view.imageMatrix)
                savedMatrix.set(matrix)
                startPoint[event.x] = event.y
                mode = DRAG
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                oriDis = distance(event)
                if (oriDis > 10f) {
                    savedMatrix.set(matrix)
                    midPoint = middle(event)
                    mode = ZOOM
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> mode = NONE
            MotionEvent.ACTION_MOVE ->
                if (mode == DRAG) {
                    // 是一个手指拖动
                    matrix.set(savedMatrix)
                    matrix.postTranslate(event.x - startPoint.x, event.y - startPoint.y)
                } else if (mode == ZOOM) {
                    // 两个手指滑动
                    val newDist = distance(event)
                    if (newDist > 10f) {
                        matrix.set(savedMatrix)
                        val scale = newDist / oriDis
                        matrix.postScale(scale, scale, midPoint.x, midPoint.y)
                    }
                }
        }
        // 设置ImageView的Matrix
        view.imageMatrix = matrix
        return true
    }

    // 计算两个触摸点之间的距离
    private fun distance(event: MotionEvent): Float {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return Math.sqrt((x * x + y * y).toDouble()).toFloat()
    }

    // 计算两个触摸点的中点
    private fun middle(event: MotionEvent): PointF {
        val x = event.getX(0) + event.getX(1)
        val y = event.getY(0) + event.getY(1)
        return PointF(x / 2, y / 2)
    }

    companion object {
        // 不同状态的表示：
        private const val NONE = 0
        private const val DRAG = 1
        private const val ZOOM = 2
    }
}