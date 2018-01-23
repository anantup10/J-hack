package com.example.anant.jhack;

/**
 * Created by JITESH on 07-01-2018.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.LocaleList;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


/**
 * Created by JITESH on 29-12-2017.
 */

public class Language extends AppCompatActivity{
    TextView textView;
    ListView listView;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.language_list);
        Language l = (new Language());
        try{

            l.setLocale(Language.this);
        }
        catch (NullPointerException e){

        }

        textView= (TextView) findViewById(R.id.tv);
        listView= (ListView) findViewById(R.id.listview);
        button= (Button) findViewById(R.id.button);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(position==0)
                {
                    Toast.makeText(Language.this,"you selected English",Toast.LENGTH_SHORT).show();
                    saveLocaleLanguage("en");
                }
                else if(position==1)
                {
                    Toast.makeText(Language.this,"you selected hindi",Toast.LENGTH_SHORT).show();
                    saveLocaleLanguage("hi");
                }
                else if(position==2)
                {
                    Toast.makeText(Language.this,"you selected punjabi",Toast.LENGTH_SHORT).show();
                    saveLocaleLanguage("pa");
                }
                else if(position==3)
                {
                    Toast.makeText(Language.this,"you selected marathi",Toast.LENGTH_SHORT).show();
                    saveLocaleLanguage("mr");
                }
                else if(position==4)
                {
                    Toast.makeText(Language.this,"you selected gujrati",Toast.LENGTH_SHORT).show();
                    saveLocaleLanguage("gu");
                }
                else if(position==5)
                {
                    Toast.makeText(Language.this,"you selected tamil",Toast.LENGTH_SHORT).show();
                    saveLocaleLanguage("ta");
                }
                else if(position==6)
                {
                    Toast.makeText(Language.this,"you selected telugu",Toast.LENGTH_SHORT).show();
                    saveLocaleLanguage("te");
                }
                else if(position==7)
                {
                    Toast.makeText(Language.this,"you selected bengali",Toast.LENGTH_SHORT).show();
                    saveLocaleLanguage("bn");
                }
                else if(position==8)
                {
                    Toast.makeText(Language.this,"you selected malayalam",Toast.LENGTH_SHORT).show();
                    saveLocaleLanguage("ml");
                }
                else if(position==9)
                {
                    Toast.makeText(Language.this,"you selected kannada",Toast.LENGTH_SHORT).show();
                    saveLocaleLanguage("kn");
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Language.this, DashBoard.class));
                finish();
            }
        });

    }

    void saveLocaleLanguage(String lang){
        SharedPreferences pref =getSharedPreferences("lang",MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString("my_current_lang",lang);
        editor.commit();
        Log.d("abc", "saveLocaleLanguage: "+lang);
        setLocale(Language.this);

    }

    public void setLocale(Context context)
    {
        SharedPreferences pref=context.getSharedPreferences("lang",MODE_PRIVATE);
        String locale_lang=pref.getString("my_current_lang","en");
        Log.d("1234", "setLocale: "+locale_lang);
        Locale myLocale=new Locale(locale_lang);
        Resources res=context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        textView.setText(R.string.choose_lan);
        button.setText(R.string.lan_button);




    }
}
