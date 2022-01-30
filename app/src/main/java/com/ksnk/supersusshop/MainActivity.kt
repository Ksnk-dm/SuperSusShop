package com.ksnk.supersusshop

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class MainActivity : AppCompatActivity() {
    private var shopWebView: WebView? = null
    private val urlLoad = "https://www.ukr.net/"
    lateinit var mAdView: AdView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        initAd()
        setSettingWebView()
    }

    override fun onBackPressed() {
        shopWebView?.goBack()
    }

    private fun init() {
        mAdView = findViewById(R.id.adView)
        shopWebView = findViewById(R.id.shop_web_view)
    }

    private fun initAd() {
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setSettingWebView() {
        CookieManager.getInstance().setAcceptThirdPartyCookies(shopWebView, true)
        shopWebView?.webChromeClient = ShopChromeClient(this)
        shopWebView?.settings?.javaScriptEnabled = true
        shopWebView?.settings?.allowContentAccess = true
        shopWebView?.settings?.domStorageEnabled = true
        shopWebView?.settings?.javaScriptCanOpenWindowsAutomatically = true
        shopWebView?.webViewClient = ShopWebViewClient()
        shopWebView?.loadUrl(urlLoad)
    }
}