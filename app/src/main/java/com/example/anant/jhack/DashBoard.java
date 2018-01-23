package com.example.anant.jhack;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;

import static android.support.v4.view.MenuItemCompat.getActionProvider;


public class DashBoard extends AppCompatActivity {
     ShareActionProvider mShareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Language l = (new Language());
        try{

            l.setLocale(DashBoard.this);
        }
        catch (NullPointerException e){

        }
        setContentView(R.layout.activity_dash_board);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_me,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int getSelectedItemId=item.getItemId();
        MenuItem Item = item;
        if(getSelectedItemId==R.id.language1)
        {
            Intent intent=new Intent(DashBoard.this,Language.class);
            startActivity(intent);
            finish();
        }

        else if(getSelectedItemId==R.id.about)
        {
            Intent intent=new Intent(DashBoard.this,AboutUs.class);
            startActivity(intent);
        }
        else if(getSelectedItemId==R.id.contact)
        {
            Intent intent=new Intent(DashBoard.this,ContactUs.class);
            startActivity(intent);
        }
        else if(getSelectedItemId==R.id.faq)
        {
            Intent intent=new Intent(DashBoard.this,Faqs.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    public void user(View v)
    {
        Intent i= new Intent(DashBoard.this,User.class);
        startActivity(i);
    }
    public void dot(View v)
    {
        Intent i= new Intent(DashBoard.this,dot.class);
        startActivity(i);

    }
   }
