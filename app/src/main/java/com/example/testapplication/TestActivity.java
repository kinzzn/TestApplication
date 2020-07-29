package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TestActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle svaedInstanceState){
        super.onCreate(svaedInstanceState);
        setContentView(R.layout.hello_world_layout);
        Log.d("TestActivity", "on Create Debug");
    }

}
