package com.ksnk.supersusshop

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity


class ShopWebViewClient(private val context: Context) : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        if (url.startsWith("tel:") || url.startsWith("whatsapp:")) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "Whatsapp not installed", Toast.LENGTH_SHORT).show()
            }

            return true
        }
        if (url.startsWith("tel:") || url.startsWith("viber:")) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "Viber not installed", Toast.LENGTH_SHORT).show()
            }
            return true
        }

        if (url.startsWith("tel:")) {
            val intent = Intent(Intent.EXTRA_PHONE_NUMBER)
            intent.data = Uri.parse(url)
            context.startActivity(intent)
            return true
        }
        view.loadUrl(url)
        return true
    }


    override fun onPageFinished(view: WebView?, url: String?) {
        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().acceptCookie()
        CookieManager.getInstance().flush()
    }
}