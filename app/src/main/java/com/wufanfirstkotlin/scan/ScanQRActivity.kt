package com.wufanfirstkotlin.scan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.king.zxing.CameraScan
import com.wufanfirstkotlin.BaseActivity
import com.wufanfirstkotlin.R

class ScanQRActivity : BaseActivity() {
    val REQUEST_CODE_SCAN = 0X01
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qr)

        findViewById<Button>(R.id.open_scan).setOnClickListener {
            startActivityForResult(Intent(this, CaptureActivity::class.java), REQUEST_CODE_SCAN)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result: String? = CameraScan.parseScanResult(data)
        result?.let {
            //toast(it)
        }

    }
}