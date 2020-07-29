package com.example.testapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
//import android.content.pm.

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";
    private final static CrashHelper crashHelper = new CrashHelper();

    private static TextView sizeView;

    public final static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch(msg.what){
                case 1:
                    setSize((String)msg.obj);
            }
        }
    };

    private static void setSize(String obj) {
        sizeView.setText(obj);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "on Create Debug");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButtonInit();

        String packageName = "com.microsoft.sapphire.daily";

        PackageManager pm = this.getPackageManager();
        try {
            ApplicationInfo info = pm.getApplicationInfo(packageName,0);

            StringBuffer sb = new StringBuffer();
            sb.append(info.loadDescription(pm)+"\n");
//            sb.append(info.appComponentFactory+"\n");
            sb.append(info.processName+"\n");
            sb.append(info.sourceDir+"\n");

            TextView infoView = (TextView) findViewById(R.id.appInfoText);
            infoView.setText(sb.toString());

            sizeView = (TextView)findViewById(R.id.appSize);
            SizeHelper.getPkgSize(this, packageName); //8.0之前

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
