package com.example.study5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity  implements Const{

    TextView txt2;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();

        String txt = getIntent().getStringExtra(KEY);
        txt2.setVisibility();


    }

    private  void initView(){
        txt2 = findViewById(R.id.text2);
        btn1 = findViewById(R.id.button1);
    }
}