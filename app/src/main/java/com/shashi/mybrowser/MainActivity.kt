package com.shashi.mybrowser

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.URLUtil
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wview.webViewClient = WebViewClient()

        wview.settings.javaScriptEnabled = true //To Enable JavaScript
        wview.settings.builtInZoomControls = true //To Enable Zoom Controls
        var pDialog = ProgressDialog(this@MainActivity) //Progress dialogue
        pDialog.setTitle("Message From Shashi")
        pDialog.setMessage("Please Wait While loading") //Progress message
        wview.webViewClient = object:WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pDialog.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pDialog.dismiss()
            }
        }
        var urlAddress = etUrl.text.toString()
        var urlStart = "https://www."
        btYou.setOnClickListener {
            urlAddress = urlStart + "youtube.com"
            wview.loadUrl(urlAddress)
        }
        btFb.setOnClickListener {
            urlAddress = urlStart + "facebook.com"
/*
            Toast.makeText(this@MainActivity,"...Searching...",Toast.LENGTH_SHORT).show()
*/
            wview.loadUrl(urlAddress)
        }
        btGoogle.setOnClickListener {
            urlAddress = urlStart + "google.com"
            wview.loadUrl(urlAddress)
        }
        btHtml.setOnClickListener {
            wview.addJavascriptInterface(this@MainActivity,"LoginHtmlPageOpen")
            wview.loadUrl("file:///android_asset/login.html")
            Log.i("MyMsg","The Html button Clicked")
        }
    } //onCreate
    fun gotoLink(view : View){
        wview.webViewClient = WebViewClient()
        var urlAddr = etUrl.text.toString()
        if(urlAddr.isNotEmpty()){
            if(!URLUtil.isNetworkUrl(urlAddr)){
                urlAddr = "https://www.google.com/search?q="+urlAddr
            }
            wview.loadUrl(urlAddr)
        }
        else{
            Toast.makeText(this@MainActivity,"Please enter the link",Toast.LENGTH_SHORT).show()
        }
    }//gotoLink
    @JavascriptInterface
    fun login(uName:String,pass:String){
        Log.i("MyMsg","Function Login is called with the value of uName=$uName and pwd= $pass")
        if(pass.isEmpty() || uName.isEmpty()){
            Toast.makeText(this@MainActivity,"Username or password field must not be empty!",Toast.LENGTH_SHORT).show()
        }
        else if(pass.equals(uName.toString()+"@123")){
            Toast.makeText(this@MainActivity,"Login SuccessFul!",Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(this@MainActivity,"Wrong Username or Password! Login Failed!",Toast.LENGTH_SHORT).show()
    }
}
