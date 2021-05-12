package main.com.gstsolution;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    ProgressBar p;
    int count;
    TextView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        p = (ProgressBar) findViewById(R.id.SplashProgressBar);
        loading = (TextView) findViewById(R.id.textSplashLoading);

        new CountDownTimer(5000, 500) {

            public void onTick(long millisUntilFinished) {
                count = count+1;
                if(count == 9){
                    p.setProgress(100);
                    loading.setText("loading complete");
                }else{
                    p.setProgress(count*10);
                    loading.setText("loading please wait...");
                }
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                Intent i = new Intent(Splash.this,MainActivity.class);
                startActivity(i);
                finish();
            }

        }.start();

    }
}
