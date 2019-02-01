package com.shashi.mybrowser

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wview.webViewClient = WebViewClient()
        var urlAddress = etUrl.text.toString()
        var urlStart = "https://www."
        btYou.setOnClickListener {
            urlAddress = urlStart + "youtube.com"
            Toast.makeText(this@MainActivity,"...Searching...",Toast.LENGTH_SHORT).show()
            wview.loadUrl(urlAddress)
        }
        btFb.setOnClickListener {
            urlAddress = urlStart + "facebook.com"
            Toast.makeText(this@MainActivity,"...Searching...",Toast.LENGTH_SHORT).show()
            wview.loadUrl(urlAddress)
        }
        btGoogle.setOnClickListener {
            urlAddress = urlStart + "google.com"
            Toast.makeText(this@MainActivity,"...Searching...",Toast.LENGTH_SHORT).show()
            wview.loadUrl(urlAddress)
        }
    }
    fun gotoLink(view : View){
        wview.webViewClient = WebViewClient()
        var urlAddr = etUrl.text.toString()
        if(urlAddr.isNotEmpty()){
            if(!URLUtil.isNetworkUrl(urlAddr)){
                urlAddr = "https://www.google.com/search?q="+urlAddr
            }
            Toast.makeText(this@MainActivity,"...Searching...",Toast.LENGTH_SHORT).show()
            wview.loadUrl(urlAddr)
        }
        else{
            Toast.makeText(this@MainActivity,"Please enter the link",Toast.LENGTH_SHORT).show()
        }

    }
}
