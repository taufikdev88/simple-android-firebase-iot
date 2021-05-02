package com.example.mysmarthome.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mysmarthome.MainActivity;
import com.example.mysmarthome.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragControl extends Fragment {
    boolean isBtn1On = false;
    boolean isBtn2On = false;
    boolean isBtn3On = false;
    boolean isBtn4On = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_control, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference relay1ref = MainActivity.database.getReference("relay1");
        DatabaseReference relay2ref = MainActivity.database.getReference("relay2");
        DatabaseReference relay3ref = MainActivity.database.getReference("relay3");
        DatabaseReference relay4ref = MainActivity.database.getReference("relay4");

        Button btnRelay1 = view.findViewById(R.id.btnRelay1);
        Button btnRelay2 = view.findViewById(R.id.btnRelay2);
        Button btnRelay3 = view.findViewById(R.id.btnRelay3);
        Button btnRelay4 = view.findViewById(R.id.btnRelay4);

        relay1ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(Integer.class) == 1) isBtn1On = true;
                else isBtn1On = false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("DEBUG", error.getMessage());
            }
        });

        relay2ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(Integer.class) == 1) isBtn2On = true;
                else isBtn2On = false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("DEBUG",error.getMessage());
            }
        });

        relay3ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(Integer.class) == 1) isBtn3On = true;
                else isBtn3On = false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("DEBUG",error.getMessage());
            }
        });

        relay4ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(Integer.class) == 1) isBtn4On = true;
                else isBtn4On = false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnRelay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBtn1On) relay1ref.setValue(0);
                else relay1ref.setValue(1);
            }
        });

        btnRelay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBtn2On) relay2ref.setValue(0);
                else relay2ref.setValue(1);
            }
        });

        btnRelay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBtn3On) relay3ref.setValue(0);
                else relay3ref.setValue(1);
            }
        });

        btnRelay4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBtn4On) relay4ref.setValue(0);
                else relay4ref.setValue(1);
            }
        });
    }
}
