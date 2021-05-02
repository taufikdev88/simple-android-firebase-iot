package com.example.mysmarthome.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mysmarthome.MainActivity;
import com.example.mysmarthome.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FragDisplay extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseReference suhuref = MainActivity.database.getReference("suhu");
        DatabaseReference kelembapan = MainActivity.database.getReference("kelembapan");

        TextView txtSuhu = view.findViewById(R.id.txtDisplaySuhu);
        TextView txtKelembapan = view.findViewById(R.id.txtDisplayKelembapan);

        suhuref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtSuhu.setText(snapshot.getValue(Float.class).toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("DEBUG",error.getMessage());
            }
        });

        kelembapan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtKelembapan.setText(snapshot.getValue(Float.class).toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("DEBUG", error.getMessage());
            }
        });
    }
}
