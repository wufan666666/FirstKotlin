package com.wufanfirstkotlin

import android.app.Application
import android.content.Context
import com.wufanfirstkotlin.sqlite.MyDBOpenHelper
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.datatrasfer.DeviceInfoProviderDefault
import com.ximalaya.ting.android.opensdk.datatrasfer.IDeviceInfoProvider

class MyApplication : Application() {
    lateinit var myDBHelper: MyDBOpenHelper
    lateinit var mXimalaya: CommonRequest
    var oaid: String = ""

    override fun onCreate() {
        super.onCreate()
        mXimalaya = CommonRequest.getInstanse();
        if (DTransferConstants.isRelease) {
            val mAppSecret = "8646d66d6abe2efd14f2891f9fd1c8af"
            mXimalaya.setAppkey("9f9ef8f10bebeaa83e71e62f935bede8")
            mXimalaya.setPackid("com.app.test.android")
            mXimalaya.init(this, mAppSecret, true, getDeviceInfoProvider(this))
        } else {
            val mAppSecret = "0a09d7093bff3d4947a5c4da0125972e"
            mXimalaya.setAppkey("f4d8f65918d9878e1702d49a8cdf0183")
            mXimalaya.setPackid("com.ximalaya.qunfeng")
            mXimalaya.init(this, mAppSecret, getDeviceInfoProvider(this))
        }
    }
    private fun getDeviceInfoProvider(context: Context?): IDeviceInfoProvider? {
        return object : DeviceInfoProviderDefault(context) {
            override fun oaid(): String {
                // 合作方要尽量优先回传用户真实的oaid，使用oaid可以关联并打通喜马拉雅主app中记录的用户画像数据，对后续个性化推荐接口推荐给用户内容的准确性会有极大的提升！
                return oaid
            }
        }
    }
}