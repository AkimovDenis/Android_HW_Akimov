package com.example.hwlesson5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textViewName = findViewById(R.id.textViewName);
        Button btnUpperCase = findViewById(R.id.btnUpperCase);
        Button btnOriginal = findViewById(R.id.btnOriginal);

        userName = getIntent().getStringExtra("USER_NAME");
        textViewName.setText(userName);

        btnUpperCase.setOnClickListener(v -> returnResult(userName.toUpperCase()));

        btnOriginal.setOnClickListener(v -> returnResult(userName));
    }

    private void returnResult(String result) {
        Intent intent = new Intent();
        intent.putExtra("MODIFIED_NAME", result);
        setResult(RESULT_OK, intent);
        finish();
    }
}
