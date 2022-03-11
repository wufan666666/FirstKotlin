package com.wufanfirstkotlin.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MoveCircleView(context: Context,attributeSet: AttributeSet) : View(context,attributeSet) {



    var a = 50.0f
    var b = 50.0f

    var paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        paint.color = Color.BLUE
        canvas?.drawCircle(a,b,30f,paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        a= event?.x!!
        b= event?.y!!

        this.invalidate()
        return true
    }


}