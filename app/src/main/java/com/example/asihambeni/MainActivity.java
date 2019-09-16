package com.example.asihambeni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView MainImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainImage = (ImageView) findViewById(R.id.MainImage);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.splash);
        MainImage.startAnimation(myanim);

        final Intent i = new Intent(this,SignInActivity.class);
        Thread timer = new Thread(){
            public void run()
            {
                try{
                    sleep(5000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
