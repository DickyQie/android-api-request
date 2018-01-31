package menu.retrofitrxjavademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import menu.retrofitrxjavademo.ui.RetrofitActivity;
import menu.retrofitrxjavademo.ui.RxJavaActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRetrofit(View view)
    {
        Intent intent=new Intent(this, RetrofitActivity.class);
        startActivity(intent);
    }
    public void onRxJava(View view)
    {
        Intent intent=new Intent(this, RxJavaActivity.class);
        startActivity(intent);
    }

}
