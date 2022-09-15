# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontwarn okio.**
-keep class okio.** { *;}

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *;}

-dontwarn com.ximalaya.ting.android.player.**
-keep class com.ximalaya.ting.android.player.** { *;}

-dontwarn com.google.gson.**
-keep class com.google.gson.** {*;}

-dontwarn org.litepal.**
-keep class org.litepal.** {*;}

-keep class com.ximalaya.ting.android.opensdk.** {*;}
-keep interface com.ximalaya.ting.android.opensdk.** {*;}
-keep public enum com.ximalaya.ting.android.opensdk.** { *; }

#厂商的混淆规则
-keep class android.os.SystemProperties
-dontwarn android.os.SystemProperties
-keep class com.huawei.**{*;}
-keep class com.meizu.**{*;}
-keep class com.xiaomi.**{*;}

-dontwarn com.huawei.**
-dontwarn com.meizu.**
-dontwarn com.xiaomi.**
-keep class com.mob.**{*;}
-dontwarn com.mob.**
-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keep class com.huawei.hms.**{*;}
-keep class com.meizu.cloud.**{*;}
-keep class com.xiaomi.mipush.sdk.**{*;}
-keep class org.apache.thrift.**{*;}
-keep class com.google.** {*;}
-keep class com.coloros.** {*;}
-dontwarn com.huawei.hms.**
-dontwarn com.meizu.cloud.**
-dontwarn com.xiaomi.mipush.sdk.**
-dontwarn org.apache.thrift.**
-dontwarn com.google.**
-dontwarn com.coloros.**

-dontwarn com.vivo.push.**
-keep class com.vivo.push.**{*; }
-keep class com.vivo.vms.**{*; }
-keep class com.mob.pushsdk.plugins.vivo.PushVivoReceiver{*;}

-keep class com.meizu.cloud.pushsdk.MzPushMessageReceiver{
public *;
}

-keep class com.mob.pushsdk.plugins.xiaomi.PushXiaoMiRevicer {*;}
-dontwarn com.xiaomi.push.**

#这是oppo的混淆规则
-keep public class * extends android.app.Service
-keep class com.heytap.msp.** { *;}
-keep class com.mob.pushsdk.plugins.oppo.** { *;}