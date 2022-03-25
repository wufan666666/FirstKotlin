package com.wufanfirstkotlin.surfaceview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.wufanfirstkotlin.R

class SurfaceViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surface_view)
        var surface = findViewById<SurfaceViewTemplate>(R.id.surface)

        findViewById<Button>(R.id.reset).setOnClickListener {
            surface.reDraw()
        }
    }
}