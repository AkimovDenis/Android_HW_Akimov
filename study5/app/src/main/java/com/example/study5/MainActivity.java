package com.example.study5;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Const {

    TextView txt1;
    TextView txt2;
    Button btn1;
    Button btn2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(KEY,"привет");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent);
            }
        });
    }

    private  void initView(){
        txt1 = findViewById(R.id.text1);
        txt2 = findViewById(R.id.text2);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
    }
}