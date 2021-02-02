package com.internsaala.shamachar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val detailWebView: WebView = findViewById(R.id.detailWebView)

        val url = intent.getStringExtra("URL")
        if(url != null){

            detailWebView.settings.javaScriptEnabled = true

            detailWebView.settings.userAgentString = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"

            detailWebView.webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility = View.GONE
                    detailWebView.visibility = View.VISIBLE

                }
            }
            detailWebView.loadUrl(url)
        }

    }
}