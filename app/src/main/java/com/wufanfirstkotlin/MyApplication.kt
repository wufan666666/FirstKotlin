package com.wufanfirstkotlin

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import com.mob.MobSDK
import com.mob.pushsdk.MobPush
import com.mob.pushsdk.MobPushCustomMessage
import com.mob.pushsdk.MobPushNotifyMessage
import com.mob.pushsdk.MobPushReceiver
import com.tencent.mmkv.MMKV
import com.wufanfirstkotlin.himalaya.utils.L
import com.wufanfirstkotlin.sqlite.MyDBOpenHelper
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.datatrasfer.DeviceInfoProviderDefault
import com.ximalaya.ting.android.opensdk.datatrasfer.IDeviceInfoProvider
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager
import org.json.JSONException
import org.json.JSONObject

open class MyApplication : Application() {
    lateinit var myDBHelper: MyDBOpenHelper
    lateinit var mXimalaya: CommonRequest
    companion object {
        var handler: Handler? = null
        var sContext: Context? = null
        fun getMyHandler(): Handler? {
            return handler
        }
        fun getAppContext(): Context? {
            return sContext
        }
    }


    var oaid: String = ""


    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
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
        handler = Handler(Looper.myLooper()!!);
        //初始化喜马拉雅播放器
        XmPlayerManager.getInstance(this).init()
        sContext = baseContext
        MobSDK.submitPolicyGrantResult(true, null)
        //获取推送RegistrationId
        MobPush.getRegistrationId { rid ->
            L.e("RegistrationId==", rid + "")
        }

        MobPush.addPushReceiver(object : MobPushReceiver {
            override fun onCustomMessageReceive(context: Context, message: MobPushCustomMessage) {
                //接收自定义消息
            }

            override fun onNotifyMessageReceive(context: Context, message: MobPushNotifyMessage) {
                //接收通知消息
                L.e("MobPush2 =接收通知消息=", message.toString() + "")
            }

            override fun onNotifyMessageOpenedReceive(
                context: Context,
                message: MobPushNotifyMessage
            ) {
                //接收通知消息被点击事件
                L.e("MobPush =接收通知消息被点击事件=", message.toString() + "")
            }

            override fun onTagsCallback(
                context: Context,
                tags: Array<String>,
                operation: Int,
                errorCode: Int
            ) {
                //接收tags的增改删查操作
                L.e(
                    "MobPush =接收tags的增改删查操作=",
                    "tags==$tags==operation==$operation==errorCode==$errorCode"
                )
            }

            override fun onAliasCallback(
                context: Context,
                alias: String,
                operation: Int,
                errorCode: Int
            ) {
                //接收alias的增改删查操作
                L.e(
                    "MobPush =接收alias的增改删查操作=",
                    "alias==$alias==operation==$operation==errorCode==$errorCode"
                )
            }
        })


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