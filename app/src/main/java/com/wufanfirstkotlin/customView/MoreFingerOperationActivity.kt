package com.wufanfirstkotlin.customView

import android.graphics.PointF
import android.opengl.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.wufanfirstkotlin.R

class MoreFingerOperationActivity : AppCompatActivity(), View.OnTouchListener {

    lateinit var imageView: ImageView

    val matrix = Matrix()
    val saveMatrix = Matrix()

    val startPoint = PointF()
    val midPoint = PointF()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_finger_operation)

        imageView = findViewById<ImageView>(R.id.imageView)

        imageView.setOnTouchListener(this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        val image = v as ImageView
        when (event?.action?.and(MotionEvent.ACTION_MASK)) {
            MotionEvent.ACTION_DOWN -> {
                matrix.apply {
                    image.imageMatrix
                }

            }
        }

        return true
    }


}



