package com.example.anant.jhack;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
ProgressBar pm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Timer t = new Timer();
        boolean checkConnection=new LoadBackgroundData().checkConnection(this);
        if (checkConnection) {
            t.schedule(new mainActivity(), 3000);
        } else {
            Toast.makeText(this, "Internet Not connected", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    class mainActivity extends TimerTask {

        @Override
        public void run() {
            Intent i = new Intent(MainActivity.this,Getting_Started.class);
            finish();
            startActivity(i);
        }

    }



}
