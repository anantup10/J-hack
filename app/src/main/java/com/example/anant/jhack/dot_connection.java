package com.example.anant.jhack;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class dot_connection extends AppCompatActivity {
    private Button connectBtn, nineBtn;
    private GestureDetectorCompat gestureDetectorCompat;

    // flag = 0 for loginFrag and flag = 1 is for regisFrag
    private int fragmentOpenFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Language l = (new Language());
        try{

            l.setLocale(dot_connection.this);
        }
        catch (NullPointerException e){

        }
        setContentView(R.layout.dot_connection);

        init();
        methodListener();
        gestureDetectorCompat = new GestureDetectorCompat(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {

                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent1, MotionEvent motionEvent2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                float x1 = motionEvent.getX();
                float y1 = motionEvent.getY();
                float x2 = motionEvent1.getX();
                float y2 = motionEvent1.getY();

                float dx = x2 - x1;
                float dy = y2 - y1;

                if (Math.abs(dx) > Math.abs(dy)) {
                    if (dx > 0) {
                        Log.d("1234", "onFling: left");

                        if (fragmentOpenFlag == 0)
                        {

                        }
                        else {
                            connectBtn.setBackgroundColor(Color.parseColor("#a8d9f5"));
                            nineBtn.setBackgroundColor(Color.parseColor("#53a8af"));
                            changeFragment(new connections());
                            fragmentOpenFlag = 0;
                        }

                    }
                    else {
                        Log.d("1234", "onFling: right");
                        if (fragmentOpenFlag == 1)
                        {

                        }
                        else {
                            connectBtn.setBackgroundColor(Color.parseColor("#53a8af"));
                            nineBtn.setBackgroundColor(Color.parseColor("#a8d9f5"));
                            changeFragment(new exceedConnection());
                            fragmentOpenFlag = 1;
                        }

                    }
                } else {
                    Log.d("1234", "onFling: y");
                }


                return false;
            }
        });
    }

    private void init()
    {

        connectBtn= (Button) findViewById(R.id.connectBtn);
        nineBtn= (Button) findViewById(R.id.nineBtn);

    }

    private void methodListener()
    {
        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new connections());
                connectBtn.setBackgroundColor(Color.parseColor("#a8d9f5"));
                nineBtn.setBackgroundColor(Color.parseColor("#53a8af"));

                fragmentOpenFlag = 0;
            }
        });

        nineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeFragment(new exceedConnection());
                connectBtn.setBackgroundColor(Color.parseColor("#53a8af"));
                nineBtn.setBackgroundColor(Color.parseColor("#a8d9f5"));
                fragmentOpenFlag = 1;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_me,menu);
        return true;
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        gestureDetectorCompat.onTouchEvent(event);

//        Log.d("1234", "onTouchEvent: "+event.getX()+", "+event.getY());

        return super.onTouchEvent(event);
    }
}