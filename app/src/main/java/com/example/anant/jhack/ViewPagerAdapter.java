package com.example.anant.jhack;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Anant on 01-01-2018.
 */

public class ViewPagerAdapter extends PagerAdapter
{private Context context;
    ViewPager vp;

    private LayoutInflater layoutInflater;
    private Integer [] image={R.drawable.dashone,R.drawable.dashtwo,R.drawable.dashthree,};
    private String[] string={"yhbkjhkihil","lkjlj;l","hl;';'k';k"};

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return image.length;
    }



    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.coustom_layout,null);

        ImageView imageView= (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(image[position]);

        vp= (ViewPager) container;
//        ViewPager ref=(ViewPager)context;
//        ref.Next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                vp.addView(view,0);
//            }
//        });
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp= (ViewPager) container;
        View view= (View) object;
        vp.removeView(view);
    }}
