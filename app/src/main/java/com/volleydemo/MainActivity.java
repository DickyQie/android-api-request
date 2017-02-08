package com.volleydemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener{

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVIew();
    }

    private void initVIew()
    {
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.button1:
                intent=new Intent(MainActivity.this,VolleyRequest.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent=new Intent(MainActivity.this,VolleyImg.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
