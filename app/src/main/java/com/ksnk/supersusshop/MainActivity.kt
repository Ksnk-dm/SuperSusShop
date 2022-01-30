package com.ksnk.supersusshop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

class MainActivity : AppCompatActivity() {

    private var shopWebView: WebView? = null
    private val shopChromeClient: ShopChromeClient = ShopChromeClient(this)
    private val urlGo = "https://www.ukr.net/"
    lateinit var mAdView : AdView
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        shopWebView = findViewById(R.id.shop_web_view)
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(shopWebView, true)
        } else CookieManager.getInstance().setAcceptCookie(true)
        shopWebView?.setWebChromeClient(shopChromeClient)
        shopWebView?.getSettings()?.setJavaScriptEnabled(true)
        shopWebView?.getSettings()?.setAllowContentAccess(true)
        shopWebView?.getSettings()?.setDomStorageEnabled(true)
        shopWebView?.getSettings()?.setJavaScriptCanOpenWindowsAutomatically(true)
        shopWebView?.setWebViewClient(object : WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            override fun onPageFinished(view: WebView, url: String) {
                CookieManager.getInstance().setAcceptCookie(true)
                CookieManager.getInstance().acceptCookie()
                CookieManager.getInstance().flush()
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        })

        checkStart(urlGo, shopWebView!!)
    }


    private fun checkStart(first: String, webView: WebView) {
        webView.loadUrl(first)
    }

    override fun onBackPressed() {
        shopWebView?.goBack()
    }
}