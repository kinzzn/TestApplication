package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private final CrashHelper crashHelper = new CrashHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "on Create Debug");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButtonInit();



        PackageManager pm = this.getPackageManager();
        try {
            ApplicationInfo info = pm.getApplicationInfo("com.example.testapplication",0);
            TextView infoView = (TextView) findViewById(R.id.appInfoText);
            infoView.setText(info.toString());
            System.out.println(info.toString());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void ButtonInit() {
        Button nullButton = (Button) findViewById(R.id.nullButton);
        nullButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Null button",
                    Toast.LENGTH_SHORT).show();
            }
        });

        Button threadBlockButton = (Button) findViewById(R.id.threadBlockButton);
        threadBlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, crashHelper.threadBlockFunc(),
                    Toast.LENGTH_LONG).show();
            }
        });

        Button nullPointerButton = (Button) findViewById(R.id.nullPointerButton);
        nullPointerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, crashHelper.nullPointerFunc(),
                    Toast.LENGTH_LONG).show();
            }
        });

        Button cpuOverloadButton = (Button) findViewById(R.id.cpuOverloadButton);
        cpuOverloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, crashHelper.cpuOverloadFunc(),
                    Toast.LENGTH_LONG).show();
            }
        });

        Button memoryLeakButton = (Button) findViewById(R.id.memoryLeakButton);
        memoryLeakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, crashHelper.memoryLeakFunc(),
                    Toast.LENGTH_LONG).show();
            }
        });

        Button exceptionButton = (Button) findViewById(R.id.exceptionButton);
        exceptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, crashHelper.exceptionFunc(),
                    Toast.LENGTH_LONG).show();
            }
        });

        Button errorButton = (Button) findViewById(R.id.errorButton);
        errorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, crashHelper.errorFunc(),
                    Toast.LENGTH_LONG).show();
            }
        });

        Button forceCloseButton = (Button) findViewById(R.id.forceCloseButton);
        forceCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, crashHelper.forceCloseFunc(),
                    Toast.LENGTH_LONG).show();
            }
        });


    }

}
