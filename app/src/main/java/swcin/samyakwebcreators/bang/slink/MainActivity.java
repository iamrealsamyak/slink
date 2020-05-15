package swcin.samyakwebcreators.bang.slink;

import android.Manifest;
import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import com.google.android.gms.ads.InterstitialAd;
import android.net.NetworkInfo;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Arrays;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {



    private WebView wv;
    private AdView mAdView;
    ProgressBar superProgressBar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to WRITE_EXTERNAL_STORAGE - requesting it");
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 1);
            }
        }




// Web View
        wv = (WebView) findViewById(R.id.webview);



        wv.setWebViewClient(new WebViewClient());
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setDisplayZoomControls(false);
        wv.getSettings().setAppCachePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/cache");
        wv.getSettings().setDatabasePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/databases");



        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {

            wv.loadUrl("file:///android_asset/offline.html");

        } else {

            wv.loadUrl("https://atopin.weebly.com");
        }

        wv.setDownloadListener(new DownloadListener()
        {
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimeType,
                                        long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse(url));
                request.setMimeType(mimeType);
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookies);
                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Downloading File...");
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(
                                url, contentDisposition, mimeType));
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();
            }});





    }




// Web View End
    //MENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.super_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:

                ConnectivityManager connectivityManagerRefresh = (ConnectivityManager)
                        getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfoRefresh = connectivityManagerRefresh.getActiveNetworkInfo();

                if (networkInfoRefresh == null || !networkInfoRefresh.isConnected() || !networkInfoRefresh.isAvailable()) {

                    wv.loadUrl("file:///android_asset/offline.html");

                } else {

                    wv.reload();
                }
                return true;

            case R.id.menu_forward:

                ConnectivityManager connectivityManagerForward = (ConnectivityManager)
                        getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfoForward = connectivityManagerForward.getActiveNetworkInfo();

                if (networkInfoForward == null || !networkInfoForward.isConnected() || !networkInfoForward.isAvailable()) {

                    wv.loadUrl("file:///android_asset/offline.html");

                } else {

                    onForwardPressed();
                }
                return true;
// FORWARD END


            case R.id.menu_about:
                onAboutPressed();
                return true;

                /*case R.id.menu_ad:
                onAdPressed();
                  return true;*/


//ABOUT END


            case R.id.menu_home:


            ConnectivityManager connectivityManagerHome = (ConnectivityManager)
                    getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfoHome = connectivityManagerHome.getActiveNetworkInfo();

            if (networkInfoHome == null || !networkInfoHome.isConnected() || !networkInfoHome.isAvailable()) {

                wv.loadUrl("file:///android_asset/offline.html");

            } else {

                onNewPressed();
            }

                return true;

            //BACK

            case R.id.menu_back:
                ConnectivityManager connectivityManagerBack = (ConnectivityManager)
                        getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfoBack = connectivityManagerBack.getActiveNetworkInfo();

                if (networkInfoBack== null || !networkInfoBack.isConnected() || !networkInfoBack.isAvailable()) {

                    wv.loadUrl("file:///android_asset/offline.html");

                } else {

                    onBackMenuPressed();
                }
                return true;

               // BACK END



            case R.id.menu_private:
                onPrivatePressed();
                return true;
            case R.id.menu_dark:
                onDarkPressed();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
        }

    private void onForwardPressed(){

        if(wv.canGoForward()){

            wv.goForward();

        } else{
            Toast.makeText(this, "Can't go Forward Anymore !", Toast.LENGTH_SHORT).show();
        }

    }
    private void onBackMenuPressed(){

        if(wv.canGoBack()){

            wv.goBack();

        } else{
            Toast.makeText(this, "Can't go Backward Anymore !", Toast.LENGTH_SHORT).show();
        }

    }
    private void onDarkPressed(){

        Intent homeIntent = new Intent(MainActivity.this, MainActivityDark.class);
        startActivity(homeIntent);
    }
    private void onAboutPressed(){

        Intent homeIntent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(homeIntent);
    }
    private void onPrivatePressed(){

        Intent homeIntent = new Intent(MainActivity.this, PrivateActivity.class);
        startActivity(homeIntent);

        Toast.makeText(this, "Private Mode Started!", Toast.LENGTH_SHORT).show();
    }
    private void onNewPressed(){

        Intent homeIntent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(homeIntent);
    }

//MENU END

    //BACK KEY
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
            wv.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

}