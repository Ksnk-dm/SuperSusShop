package com.ksnk.supersusshop

import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient

class ShopWebViewClient: WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().acceptCookie()
        CookieManager.getInstance().flush()
    }
}