package com.wufanfirstkotlin.viewPractice

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.wufanfirstkotlin.R

class WebViewActivity: AppCompatActivity() {
    private lateinit var webView:WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        webView =findViewById(R.id.web_view)

        webView.settings.javaScriptEnabled = true
        webView.loadUrl("file:///android_asset/hello.html")
        webView.loadUrl("https://m.baidu.com")
    }
}