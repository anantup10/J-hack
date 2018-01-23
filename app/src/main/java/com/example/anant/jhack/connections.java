package com.example.anant.jhack;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Random;

import static com.example.anant.jhack.R.id.submit;

public class connections extends Fragment {
    private EditText adhar;
    private Button submit,logout;
    String aadhar;
    ListView lv1;
    List<String> value;
    final int PERMISSIBLE_MOBILES_PER_AADHAR=20;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static final String ADMIN_LONDA = "8952079471";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_connections, null);

        Button connectBtn = (Button) view.findViewById(R.id.connectBtn);
        lv1= (ListView) view.findViewById(R.id.lv1);
        submit = (Button) view.findViewById(R.id.submit);
        logout = (Button) view.findViewById(R.id.logout);

        adhar= (EditText) view.findViewById(R.id.adhar);

        connections.this.adhar.setText("");


  /*      adhar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                submit.setEnabled(!TextUtils.isEmpty(s.toString().trim()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        adhar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                submit.setVisibility(View.INVISIBLE);
                return false;
            }
        });*/

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intentBackToMainActivity = new Intent(getActivity(), dot.class);
                startActivity(intentBackToMainActivity);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aadhar = adhar.getText().toString();


                if(aadhar.equals(null)){
                    Toast.makeText(getActivity(), "Empty feild", Toast.LENGTH_SHORT).show();
                    Log.d("545454", "onClick: sallmob"+aadhar);
                }
                else if(aadhar != null) {
                    Log.d("aadhar-null-kyu-hai", "onClick: "+aadhar.equals(null));
                    DatabaseReference ref0 = database.getReference().child(aadhar);
                    check2callGenerateListOfMobiles(ref0);
                }
               // else
                 //   Toast.makeText(getActivity(), " Kindly Enter Your aadhar number first", Toast.LENGTH_LONG).show();
//
                }

        });


        return view;
    }


    // To check wether entered aadhar no. is invalid or not     [4]
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

    public void notifyUserOfEmptyField(){
        Toast.makeText(getActivity(), "enter correct aadhar no. ", Toast.LENGTH_SHORT).show();
    }

    public void check2callGenerateListOfMobiles(DatabaseReference dbRef){
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() == 0){
                    Toast.makeText(getActivity(), "You have entered an invalid AADHAR no.", Toast.LENGTH_SHORT).show();
                    lv1.setAdapter(null);
                }
                else{
                    DatabaseReference refNew1 = database.getReference(aadhar);
                    generateListOfMobiles(refNew1);
                    Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getActivity(), "zindagi", Toast.LENGTH_SHORT).show();
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
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_list_for_mobile_numbers, R.id.custmlistformobile_textview_mobile_numbers,value2);
                lv1.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("read-db-error", "Failed to read value.", databaseError.toException());
                Toast.makeText(getActivity(), "Failed to read value ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}