package com.ksnk.supersusshop

import android.annotation.SuppressLint
import android.content.Context
import android.os.Message
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebView.WebViewTransport


class ShopChromeClient (private val context:Context) : WebChromeClient()  {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateWindow(view: WebView?, isDialog: Boolean,
                                isUserGesture: Boolean, resultMsg: Message
    ): Boolean {
        val newWebView = WebView(context)
        newWebView.settings.javaScriptEnabled = true
        newWebView.webChromeClient = this
        newWebView.settings.javaScriptCanOpenWindowsAutomatically = true
        newWebView.settings.domStorageEnabled = true
        newWebView.settings.setSupportMultipleWindows(true)
        val transport = resultMsg.obj as WebViewTransport
        transport.webView = newWebView
        resultMsg.sendToTarget()

        return true
    }
}