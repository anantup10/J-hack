package com.example.anant.jhack;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class exceedConnection extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ListView listShowExceeding;
    List<String> value;
    TextView url;
    String TAG = "lassun";
    String greater = "greaterThan9";
    DatabaseReference ref;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_exceed_connection, null);

        Button nineBtn = (Button) view.findViewById(R.id.nineBtn);
        listShowExceeding = (ListView) view.findViewById(R.id.listShowExceeding);
        url = (TextView) view.findViewById(R.id.dotexceed9_textview_show_exceed9);


            ref  = database.getReference("greaterThan9");
        url.setMovementMethod(LinkMovementMethod.getInstance());
        url.setText(""+ref);
                Toast.makeText(getActivity(), "link : "+ref, Toast.LENGTH_SHORT).show();

                //ref.addValueEventListener()

                DatabaseReference ref9 = database.getReference().child(greater);
                generateListOfMobiles(ref9);



        Toast.makeText(getActivity(), "link : "+ref, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate: "+ref);


        return view;
    }


//    public void check2callGenerateListOfMobiles(DatabaseReference dbRef, final String grt)
//    {
//
//        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                // you may  check size getChildrenCount
//
//                DatabaseReference refNew1 = database.getReference(grt).child(greater);
//                generateListOfMobiles(refNew1);
//
//                Toast.makeText(getActivity(), "  ", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    }


    public void generateListOfMobiles(DatabaseReference x)
    {

        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>(){};

//                if(dataSnapshot.getValue(t).equals(null)){
//                    value.clear();
//                }
//                else{
                    value = dataSnapshot.getValue(t);
                //}
                String[] value2 = new String[value.size()];
                value2 = value.toArray(value2);

                int k=0;

                for (String i : value){
                    if(i.equals(null))
                        value2[k]="nul";
                    else
                        value2[k] = i;
                k++;
                }
                k=0;

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_list_for_mobile_numbers, R.id.custmlistformobile_textview_mobile_numbers, value2);
                listShowExceeding.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled: "+value, databaseError.toException() );
            }
        });

    }



}
