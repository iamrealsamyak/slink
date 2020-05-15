package swcin.samyakwebcreators.bang.slink;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Arrays;


public class MainActivityDark extends AppCompatActivity {



    private WebView wv;
    private AdView mAdView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dark);





// Web View
        wv = (WebView) findViewById(R.id.webviewdarkmain);



        wv.setWebViewClient(new WebViewClient());
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.getSettings().setAppCachePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/cache");
        wv.getSettings().setDatabasePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/databases");

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {

            wv.loadUrl("file:///android_asset/offline_dark.html");

        } else {

            wv.loadUrl("https://atopindark.weebly.com");
        }

    }
// Web View End
    //MENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dark_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_forward:

                ConnectivityManager connectivityManagerForward = (ConnectivityManager)
                        getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfoForward = connectivityManagerForward.getActiveNetworkInfo();

                if (networkInfoForward == null || !networkInfoForward.isConnected() || !networkInfoForward.isAvailable()) {

                    wv.loadUrl("file:///android_asset/offline_dark.html");

                } else {

                    onForwardPressed();
                }
                return true;
// FORWARD END


            case R.id.menu_about:
                ConnectivityManager connectivityManagerAbout = (ConnectivityManager)
                        getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfoAbout = connectivityManagerAbout.getActiveNetworkInfo();

                if (networkInfoAbout == null || !networkInfoAbout.isConnected() || !networkInfoAbout.isAvailable()) {

                    wv.loadUrl("file:///android_asset/offline_dark.html");

                } else {

                   wv.loadUrl("file:///android_asset/about_dark.html");
                }

                return true;


//ABOUT END


            case R.id.menu_home:


                ConnectivityManager connectivityManagerHome = (ConnectivityManager)
                        getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfoHome = connectivityManagerHome.getActiveNetworkInfo();

                if (networkInfoHome == null || !networkInfoHome.isConnected() || !networkInfoHome.isAvailable()) {

                    wv.loadUrl("file:///android_asset/offline_dark.html");

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

                    wv.loadUrl("file:///android_asset/offline_dark.html");

                } else {

                    onBackMenuPressed();
                }
                return true;

            // BACK END

            case R.id.menu_refresh:
                ConnectivityManager connectivityManager = (ConnectivityManager)
                        getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfoRefresh = connectivityManager.getActiveNetworkInfo();

                if (networkInfoRefresh == null || !networkInfoRefresh.isConnected() || !networkInfoRefresh.isAvailable()) {

                    wv.loadUrl("file:///android_asset/offline_dark.html");

                } else {

                    wv.reload();
                }
                return true;

            case R.id.menu_private:
                onPrivatePressed();
                return true;

            case R.id.menu_light:
                onLightPressed();
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
    private void onPrivatePressed(){

        Intent homeIntent = new Intent(MainActivityDark.this, PrivateActivityDark.class);
        startActivity(homeIntent);

        Toast.makeText(this, "Private Mode Started!", Toast.LENGTH_SHORT).show();
    }
    private void onNewPressed(){

        Intent homeIntent = new Intent(MainActivityDark.this, MainActivityDark.class);
        startActivity(homeIntent);
    }
    private void onLightPressed(){

        Intent homeIntent = new Intent(MainActivityDark.this, MainActivity.class);
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