package com.example.meet6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class page2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        ImageView img =findViewById(R.id.img);
        TextView name=findViewById(R.id.name);
        TextView age=findViewById(R.id.age);
        TextView dis=findViewById(R.id.discription);

    }
}