package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText nameEditText,ageEditText;
    Button add,load;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText=(EditText)findViewById(R.id.nameedittextId);
        ageEditText=(EditText)findViewById(R.id.ageedittextid);
        add=(Button)findViewById(R.id.addbuttonid);
        load=(Button)findViewById(R.id.loadbuttonid);
        databaseReference= FirebaseDatabase.getInstance().getReference("Students");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),GetDataActivity.class);
                startActivity(intent);
            }
        });
    }

    private void savedata() {
        String name=nameEditText.getText().toString().trim();
        String age=ageEditText.getText().toString().trim();
        String key=databaseReference.push().getKey();
        Students student=new Students(name,age);
        databaseReference.child(key).setValue(student);
        Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
        nameEditText.setText("");
        ageEditText.setText("");
    }
}