package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GetDataActivity extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    private List<Students> studentsList;
    private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        studentsList=new ArrayList<>();
        adapter=new Adapter(GetDataActivity.this,studentsList);
        listView=(ListView)findViewById(R.id.listViewId);
        databaseReference= FirebaseDatabase.getInstance().getReference("Students");
    }

    @Override
    protected void onStart() {
        databaseReference.orderByChild("age").limitToLast(2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                studentsList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Students student=dataSnapshot.getValue(Students.class);
                    studentsList.add(student);
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}