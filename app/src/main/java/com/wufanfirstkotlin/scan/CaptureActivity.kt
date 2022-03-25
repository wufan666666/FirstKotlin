package com.wufanfirstkotlin.scan
import android.Manifest
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.view.PreviewView
import com.google.zxing.Result
import com.king.zxing.CameraScan
import com.king.zxing.CameraScan.OnScanResultCallback
import com.king.zxing.DefaultCameraScan
import com.king.zxing.ViewfinderView
import com.king.zxing.util.LogUtils
import com.king.zxing.util.PermissionUtils
import com.wufanfirstkotlin.R

/**
 * @author [Jenly](mailto:jenly1314@gmail.com)
 */
class CaptureActivity : AppCompatActivity(), OnScanResultCallback {
    protected var previewView: PreviewView? = null
    protected var viewfinderView: ViewfinderView? = null
    protected var ivFlashlight: View? = null

    /**
     * Get [CameraScan]
     * @return [.mCameraScan]
     */
    var cameraScan: CameraScan? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = layoutId
        if (isContentView(layoutId)) {
            setContentView(layoutId)
        }
        /*val dm = DisplayMetrics()
        this.getWindowManager().getDefaultDisplay().getMetrics(dm)
        val p = this.window.attributes //获取对话框当前的参数值

        p.width = dm.widthPixels //高度设置为屏幕

        p.height = dm.heightPixels/2+100 //宽度设置为全屏

        p.gravity = Gravity.CENTER
        this.window.attributes = p*/
        initUI()
    }

    /**
     * 初始化
     */
    fun initUI() {
        previewView = findViewById(previewViewId)
        val viewfinderViewId = viewfinderViewId
        if (viewfinderViewId != 0) {
            viewfinderView = findViewById<ViewfinderView>(viewfinderViewId)
        }
        val ivFlashlightId = flashlightId
        if (ivFlashlightId != 0) {
            ivFlashlight = findViewById(ivFlashlightId)
            if (ivFlashlight != null) {
                ivFlashlight!!.setOnClickListener { v: View? -> onClickFlashlight() }
            }
        }
        initCameraScan()
        startCamera()
    }

    /**
     * 点击手电筒
     */
    protected fun onClickFlashlight() {
        toggleTorchState()
    }

    /**
     * 初始化CameraScan
     */
    fun initCameraScan() {
        cameraScan = DefaultCameraScan(this, previewView!!)
        cameraScan?.setOnScanResultCallback(this)
    }

    /**
     * 启动相机预览
     */
    fun startCamera() {
        if (cameraScan != null) {
            if (PermissionUtils.checkPermission(this, Manifest.permission.CAMERA)) {
                cameraScan!!.startCamera()
            } else {
                LogUtils.d("checkPermissionResult != PERMISSION_GRANTED")
                PermissionUtils.requestPermission(this, Manifest.permission.CAMERA, CAMERA_PERMISSION_REQUEST_CODE)
            }
        }
    }

    /**
     * 释放相机
     */
    private fun releaseCamera() {
        if (cameraScan != null) {
            cameraScan!!.release()
        }
    }

    /**
     * 切换闪光灯状态（开启/关闭）
     */
    protected fun toggleTorchState() {
        if (cameraScan != null) {
            val isTorch = cameraScan!!.isTorchEnabled
            cameraScan!!.enableTorch(!isTorch)
            if (ivFlashlight != null) {
                ivFlashlight!!.isSelected = !isTorch
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            requestCameraPermissionResult(permissions, grantResults)
        }
    }

    /**
     * 请求Camera权限回调结果
     * @param permissions
     * @param grantResults
     */
    fun requestCameraPermissionResult(permissions: Array<String>, grantResults: IntArray) {
        if (PermissionUtils.requestPermissionsResult(Manifest.permission.CAMERA, permissions, grantResults)) {
            startCamera()
        } else {
            finish()
        }
    }

    override fun onDestroy() {
        releaseCamera()
        super.onDestroy()
    }

    /**
     * 返回true时会自动初始化[.setContentView]，返回为false是需自己去初始化[.setContentView]
     * @param layoutId
     * @return 默认返回true
     */
    fun isContentView(@LayoutRes layoutId: Int): Boolean {
        return true
    }

    /**
     * 布局id
     * @return
     */
    val layoutId: Int
        get() = R.layout.activity_capture

    /**
     * [.viewfinderView] 的 ID
     * @return 默认返回{@code R.id.viewfinderView}, 如果不需要扫码框可以返回0
     */
    val viewfinderViewId: Int
        get() = R.id.viewfinderView

    /**
     * 预览界面[.previewView] 的ID
     * @return
     */
    val previewViewId: Int
        get() = R.id.previewView

    /**
     * 获取 [.ivFlashlight] 的ID
     * @return  默认返回{@code R.id.ivFlashlight}, 如果不需要手电筒按钮可以返回0
     */
    val flashlightId: Int
        get() = R.id.ivFlashlight

    /**
     * 接收扫码结果回调
     * @param result 扫码结果
     * @return 返回true表示拦截，将不自动执行后续逻辑，为false表示不拦截，默认不拦截
     */
    override fun onScanResultCallback(result: Result): Boolean {
        return false
    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 0X86
    }
}