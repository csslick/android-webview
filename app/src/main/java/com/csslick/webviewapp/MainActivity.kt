package com.csslick.webviewapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.URLUtil
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.webViewClient = MyWebViewClient();    // 웹뷰 객체 생성
        webView.settings.javaScriptEnabled = true   // 자바스크립트 허용

        // val url = "https://www.ssafy.com/"
        val url = "file:///android_asset/www/index.html"
        webView.loadUrl(url)
    }

    // 웹뷰상 http이외의 URL 기능 추가 - tel, mailto, sms, geo
    inner class MyWebViewClient : WebViewClient()
    {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean
        {
            if(URLUtil.isNetworkUrl(url))
            {
                return false
            }
            try
            {
                val shareIntent= Intent()
                shareIntent.action=Intent.ACTION_VIEW
                shareIntent.data= Uri.parse(url)
                startActivity(shareIntent)
            }
            catch(e: ActivityNotFoundException)
            {
                Toast.makeText(this@MainActivity, "Appropriate app not found", Toast.LENGTH_LONG).show()
                Log.e("AndroidRide",e.toString())
            }
            return true
        }
        @RequiresApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean
        {
            val url=request?.url.toString()
            if(URLUtil.isNetworkUrl(url))
            {
                return false
            }
            try
            {
                val shareIntent= Intent()
                shareIntent.action=Intent.ACTION_VIEW
                shareIntent.data= Uri.parse(url)
                startActivity(shareIntent)
            }
            catch(e: ActivityNotFoundException)
            {
                Toast.makeText(this@MainActivity, "Appropriate app not found", Toast.LENGTH_LONG).show()
                Log.e("AndroidRide",e.toString())
            }
            return true
        }

    }

    override fun onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack()    // 이전 페이지
        } else {
            super.onBackPressed()   // 원래 기능 수행
        }

    }

}
