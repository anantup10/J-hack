package com.example.anant.jhack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
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
            Intent intent=new Intent(AboutUs.this,Language.class);
            startActivity(intent);
            finish();
        }

        else if(getSelectedItemId==R.id.about)
        {
            Intent intent=new Intent(AboutUs.this,AboutUs.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
