package kz.itstep.androidlesson3;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            Log.d("onCreate", "Первый запуск");
        } else {
            Log.d("onCreate", "Второй запуск");
        }
        setContentView(R.layout.scroll);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LogCycle", "onResume");
        toastMake("onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LogCycle", "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LogCycle", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LogCycle", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LogCycle", "onResume");
        toastMake("onResume");
    }

    protected void toastMake(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public void OnClickText(View view) {
        toastMake("Clic me");
    }
}