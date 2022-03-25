package com.wufanfirstkotlin.surfaceview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.wufanfirstkotlin.himalaya.utils.L
import java.lang.Exception

class SurfaceViewTemplate : SurfaceView, SurfaceHolder.Callback, Runnable {
    private val TAG = "SurfaceViewTemplate"
    private var surfaceHolder: SurfaceHolder? = null
    private var canvas: Canvas? = null
    private var paint: Paint? = null
    private var isDrawing = false
    private var path: Path? = null

    constructor(context: Context?) : this(context, null) {
        initView()
    }


    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs, 0) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }
    //constructor(context: Context!!, attrs: AttributeSet!!, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    private fun initView() {
        surfaceHolder = holder
        surfaceHolder!!.addCallback(this)
        path = Path()

        paint = Paint()
        paint.apply {
            this!!.style = Paint.Style.STROKE
            this!!.strokeWidth = 6F
            this!!.isAntiAlias = true
            this!!.color = Color.RED
        }
        this.isFocusable = true
        this.isFocusableInTouchMode = true
        this.keepScreenOn = true
        L.e(TAG, "initView()")

    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        isDrawing = true
        Thread(this).start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        isDrawing = false
    }

    override fun run() {
        var currentTimeMillis = System.currentTimeMillis()
        if (isDrawing) {
            draw()
            var end = System.currentTimeMillis()
            if (end - currentTimeMillis < 100) {
                Thread.sleep(100 - end + currentTimeMillis)
            }
        }

    }

    private fun draw() {
        try {
            L.e(TAG, "drawing")
            canvas = holder.lockCanvas()
            canvas!!.drawColor(Color.WHITE)
            canvas!!.drawPath(path!!, paint!!)
        } catch (e: Exception) {
            println("${e.stackTrace}")
        } finally {
            holder?.unlockCanvasAndPost(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var x = event!!.x
        var y = event!!.y

        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                path!!.moveTo(x!!, y!!)
            }
            MotionEvent.ACTION_MOVE -> {
                path!!.lineTo(x!!, y!!)
                draw()
            }
            MotionEvent.ACTION_UP -> {
                L.e(TAG, "手指抬起")
            }
        }

        return true
    }

    fun reDraw(): Boolean {
        path!!.reset()
        draw()
        return true
    }
}