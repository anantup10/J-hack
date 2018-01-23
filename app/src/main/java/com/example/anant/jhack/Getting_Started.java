package com.example.anant.jhack;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Getting_Started extends AppCompatActivity {

    android.support.v4.view.ViewPager viewPager;
    private String[] string={"Eat the Best","Choose the food you love","Sit back Dinner is on the way"};
    private String [] substring={"Access all the best dishes\nand enjoy their cuisine\nat home","Our Delivery service offers you \na widerange of fresh meals\nprepared at the moment",
            "Get ready and comfortable\nwhilist our bikers bring you\nmeal at your door"};
    LinearLayout sliderDotsPanel;
    private int dotsCount;
    int x;
    private ImageView[] dots;
    private Button startBtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Language l = (new Language());
        try{

            l.setLocale(Getting_Started.this);
        }
        catch (NullPointerException e){

        }
        setContentView(R.layout.activity_getting__started);
        init();
    }

    private void init()
    {
        viewPager= (android.support.v4.view.ViewPager) findViewById(R.id.viewPager);
        startBtn= (Button) findViewById(R.id.startBtn);
        sliderDotsPanel= (LinearLayout) findViewById(R.id.sliderDots);
        final ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
//        private int getItem() {
//            return viewPager.getCurrentItem();
//        }
        dotsCount=viewPagerAdapter.getCount();
        dots=new ImageView[dotsCount];

        for(int i=0;i<dotsCount;i++)
        {
            dots[i]=new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.unselected));
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,0,0);
            sliderDotsPanel.addView(dots[i],params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.selected));




        viewPager.addOnPageChangeListener(new android.support.v4.view.ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                for(int i=0;i<dotsCount;i++)
                {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.unselected));
                }

                x=position;
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.selected));



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //  x=position;
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(x==2)
                {
                    Toast.makeText(Getting_Started.this, "clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Getting_Started.this, Language.class));
                    finish();
                }
                else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                    //viewPagerAdapter.setCurrentItem(getItem(+1), true);
                    dots[x].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected));
                    }
            }
        });
    }
}
