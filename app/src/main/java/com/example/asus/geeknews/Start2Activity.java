package com.example.asus.geeknews;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Start2Activity extends AppCompatActivity {
    int i=1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                i--;
                if (i==0){
                    startActivity(new Intent(Start2Activity.this,MainActivity.class));
                    finish();
                }
            }
        }
    };
    private ImageView mStartimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start2);
        initView();
    }

    private void initView() {
        mStartimg = (ImageView) findViewById(R.id.startimg);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.startanim);
        mStartimg.setAnimation(animation);
        animation.start();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        };
        timer.schedule(timerTask,1000,1000);

    }
}
