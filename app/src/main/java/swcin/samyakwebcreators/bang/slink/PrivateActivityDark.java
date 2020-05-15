package swcin.samyakwebcreators.bang.slink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class PrivateActivityDark extends AppCompatActivity {

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_dark);

        // Web View
        wv = (WebView) findViewById(R.id.webviewprivatedark);



        wv.setWebViewClient(new WebViewClient());
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.private_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_forward1:
                ConnectivityManager connectivityManagerForward = (ConnectivityManager)
                        getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfoForward = connectivityManagerForward.getActiveNetworkInfo();

                if (networkInfoForward == null || !networkInfoForward.isConnected() || !networkInfoForward.isAvailable()) {

                    wv.loadUrl("file:///android_asset/offline.html");

                } else {

                    onForwardPressed();
                }

                return true;


            case R.id.menu_normal:
                onNormalPressed();
                return true;
            case R.id.menu_back1:
                ConnectivityManager connectivityManagerBack = (ConnectivityManager)
                        getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfoBack = connectivityManagerBack.getActiveNetworkInfo();

                if (networkInfoBack== null || !networkInfoBack.isConnected() || !networkInfoBack.isAvailable()) {

                    wv.loadUrl("file:///android_asset/offline_dark.html");

                } else {

                    onBackMenuPressed();
                }
                return true;
            case R.id.menu_refresh1:
                ConnectivityManager connectivityManager = (ConnectivityManager)
                        getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfoRefresh = connectivityManager.getActiveNetworkInfo();

                if (networkInfoRefresh == null || !networkInfoRefresh.isConnected() || !networkInfoRefresh.isAvailable()) {

                    wv.loadUrl("file:///android_asset/offline_dark.html");

                } else {

                    wv.reload();
                }
                return true;
            case R.id.menu_home1:
                ConnectivityManager connectivityManagerHome = (ConnectivityManager)
                        getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfoHome = connectivityManagerHome.getActiveNetworkInfo();

                if (networkInfoHome == null || !networkInfoHome.isConnected() || !networkInfoHome.isAvailable()) {

                    wv.loadUrl("file:///android_asset/offline_dark.html");

                } else {

                    onHomePressed();
                }

                return true;

            case R.id.menu_help:
                onHelpPressed();
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
    private void onHelpPressed(){

        Intent homeIntent = new Intent(PrivateActivityDark.this, PrivateHelpDark.class);
        startActivity(homeIntent);
    }
    private void onHomePressed(){

        Intent homeIntent = new Intent(PrivateActivityDark.this, PrivateActivityDark.class);
        startActivity(homeIntent);
    }


    private void onNormalPressed(){

        Intent homeIntent = new Intent(PrivateActivityDark.this, MainActivityDark.class);
        startActivity(homeIntent);
        Toast.makeText(this, "Normal Mode Started", Toast.LENGTH_SHORT).show();
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
