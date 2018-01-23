package com.example.anant.jhack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class userResult extends AppCompatActivity {
    String aadhar;
    ListView listview;
    List<String> value;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Language l = (new Language());
        try{

            l.setLocale(userResult.this);
        }
        catch (NullPointerException e){

        }
        setContentView(R.layout.activity_user_result);

        GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
        listview= (ListView) findViewById(R.id.listview);
        aadhar = getIntent().getStringExtra("aadhar");

        Log.d("545454", "onClick: sallmob"+aadhar);
        if(aadhar.equals(null)){
            aadhar="1";
            Log.d("545454", "onClick: sallmob"+aadhar);
        }
        Log.d("545454", "onClick: sallmob"+aadhar);

        if(aadhar != null) {
            Log.d("aadhar-null-kyu-hai", "onClick: "+aadhar.equals(null));
            DatabaseReference ref0 = database.getReference().child(aadhar);
            check2callGenerateListOfMobiles(ref0);
        }
        else
            Toast.makeText(userResult.this, " Kindly Enter Your aadhar number first", Toast.LENGTH_LONG).show();
        }

    public String checkAuthenticityOfAaddhar(String x)
    {
        int checkAadharReturnStatus=0;


        if(x.equals("0") || x.equals(null)){
            notifyUserOfEmptyField();
            return "null";
        }
        else{
            return x;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_me,menu);
        return true;
    }

    public void notifyUserOfEmptyField(){
        Toast.makeText(userResult.this, "enter correct aadhar no. ", Toast.LENGTH_SHORT).show();
    }

    public void check2callGenerateListOfMobiles(DatabaseReference dbRef){
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() == 0){
                    Toast.makeText(userResult.this, "You have entered an invalid AADHAR no.", Toast.LENGTH_SHORT).show();
                    listview.setAdapter(null);
                }
                else{
                    DatabaseReference refNew1 = database.getReference(aadhar);
                    generateListOfMobiles(refNew1);
                    Toast.makeText(userResult.this, "success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void  generateListOfMobiles(DatabaseReference x){

        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // value gets the whole object (String-list-type) as the leaf of every aadhar
                GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>(){};
                Log.d("121433", "onDataChange: 1233"+dataSnapshot.getValue());

                if(dataSnapshot.getValue(t).equals(null)) {
                    value.clear();
                    Toast.makeText(userResult.this, "zindagi", Toast.LENGTH_SHORT).show();
                }
                else {
                    value = dataSnapshot.getValue(t);
                }
                // to initialise string with only as many elements as their are mobile no.s linked to an aadhar.
                String[] value2 = new String[value.size()];
                value2 = value.toArray(value2);
                int k=0;
                for (String i : value){
                    if(i.equals(null))
                        value2[k]="nul-la";
                    else
                        value2[k] = i;

                    k++;
                }k=0;

                //to make listview of mobile(s)
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(userResult.this, R.layout.custom_list_for_mobile_numbers, R.id.custmlistformobile_textview_mobile_numbers,value2);
                listview.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("read-db-error", "Failed to read value.", databaseError.toException());
                Toast.makeText(userResult.this, "Failed to read value ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
