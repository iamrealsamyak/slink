package swcin.samyakwebcreators.bang.slink;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class IntroActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        new Handler().postDelayed(new Runnable(){


            @Override
            public void run(){

                Intent homeIntent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();

            }



        },SPLASH_TIME_OUT);
    }
}
