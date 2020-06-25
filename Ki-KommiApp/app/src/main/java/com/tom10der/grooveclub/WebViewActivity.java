package com.tom10der.grooveclub;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

//public class WebViewActivity extends AppCompatActivity {
public class WebViewActivity extends MainActivity {
//public class WebViewActivity extends WebViewClient{

    WebView mWebView;
    private String mapurl;
    private String url_p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.webpage);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setAppCacheMaxSize(35 * 1024 * 1024); // 15MB
        mWebView.getSettings().setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT); // load online by default

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);

        if ( !isNetworkAvailable() ) { // loading offline
            mWebView.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
        }

        String value = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("url2");
        }

        url_p1 = "http://kihaglxsapq01.int.kistler.com:8000/sap/bc/personas?sap-client=100&sap-language=DE&~transaction=ZMM_MOBILE_KOMMI&sap-personas-flavor=005056AF54911EDA88E66F5FC75CC53D~nosplash=1";
        mapurl = url_p1 + value;
        String prodapp = "nav_prodapp";

        if (value.equals(prodapp)) {
            mapurl = "";
            mapurl = "https://kihaglxsapq01.int.kistler.com/sap/bc/webdynpro/sap/zpp_prod_app?sap-client=100&sap-language=DE&sap-personas-flavor=005056AF54911ED9B98ABEA678CE4EE5~nosplash=1";
        }

        mWebView.loadUrl(mapurl);
        //mWebView.loadUrl("https://map.geo.admin.ch/?topic=ech&lang=de&bgLayer=ch.swisstopo.pixelkarte-farbe&layers=ch.swisstopo.zeitreihen,ch.bfs.gebaeude_wohnungs_register,ch.bafu.wrz-wildruhezonen_portal,ch.swisstopo.swisstlm3d-wanderwege&layers_visibility=false,false,false,false&layers_timestamp=18641231,,,&X=276828&Y=689390&zoom=7&crosshair=marker");
        mWebView.setWebViewClient(new HbMapViewClient());
        //enable Zoom function
        mWebView.getSettings().setBuiltInZoomControls(true);
        // Scrollbar style
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setScrollbarFadingEnabled(false);

    }
//----------------------------------------------------------------------------
    private boolean isNetworkAvailable() {
//----------------------------------------------------------------------------
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService( CONNECTIVITY_SERVICE );
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    /*public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;

        }// close if
        return super.onKeyDown(keyCode, event);

    //} close onKeyDown */

    //----------------------------------------------------------------------------
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    //----------------------------------------------------------------------------
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                        Toast.makeText(this, "Bitte nochmals BACK klicken um die Seite zu verlassen",
                                Toast.LENGTH_SHORT).show();

                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    //----------------------------------------------------------------------------
    private class HbMapViewClient extends WebViewClient {
   //----------------------------------------------------------------------------

        @Override
        // no display of url line
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        } // close UrlLoading

    }// close HbMapViewClient

}// close class



