package swcin.samyakwebcreators.bang.slink;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class AboutActivity extends AppCompatActivity {

    private WebView wv;
private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);




    /*wv = (WebView) findViewById(R.id.webview2);


        wv.setWebViewClient(new WebViewClient());
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);


        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {

            wv.loadUrl("file:///android_asset/offline.html");

        } else {

            wv.loadUrl("file:///android_asset/about.html");*/
        }


    }

