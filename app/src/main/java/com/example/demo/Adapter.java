package com.example.demo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter  extends ArrayAdapter <Students> {
    private Activity context;
    private List<Students>studentsList;


    public Adapter(Activity context,  List<Students> studentsList) {
        super(context, R.layout.samplelayout, studentsList);
        this.context = context;
        this.studentsList = studentsList;
    }

  @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view= layoutInflater.inflate(R.layout.samplelayout,null,true);
        Students student=studentsList.get(position);
        TextView textView1=view.findViewById(R.id.nameTextViewid);
        TextView textView2=view.findViewById(R.id.ageTextViewid);
        textView1.setText("Name: "+student.name);
        textView2.setText("Age "+student.age);
        return view;
    }
}
