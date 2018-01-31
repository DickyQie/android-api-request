package menu.retrofitrxjavademo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import menu.retrofitrxjavademo.R;
import menu.retrofitrxjavademo.entity.DepartmentInfo;
import menu.retrofitrxjavademo.util.IRequestService;
import menu.retrofitrxjavademo.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zq on 2016/8/5.
 */

public class RetrofitActivity extends Activity implements View.OnClickListener{

    private TextView mTextView;
    private String resultStr1 = "";
    private static String urls = "http://gpj.zhangwoo.cn/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_main);
        initView();
    }

    private void initView()
    {
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.Text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                get();
                break;
            case R.id.btn2:
                getQuery();
                break;
            case R.id.btn3:
                Thread visitBaiduThreads = new Thread(new VisitWebRunnables());
                visitBaiduThreads.start();
                try {
                    visitBaiduThreads.join();
                    if (!resultStr1.equals("")) {
                        mTextView.setText(resultStr1);
                        Toast.makeText(getApplicationContext(),resultStr1,Toast.LENGTH_LONG).show();
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case R.id.btn4:
                post();
                break;
            case R.id.btn5:
                postField();
                break;
            case R.id.btn6:
                postMap();
                break;

        }
    }
    private void get()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urls)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IRequestService service = retrofit.create(IRequestService.class);
        Call<DepartmentInfo> call=service.getInfo1();
        call.enqueue(new Callback<DepartmentInfo>() {
            @Override
            public void onResponse(Call<DepartmentInfo> call, Response<DepartmentInfo> response) {
                DepartmentInfo  info=response.body();
                mTextView.setText(info.getError()+"-----"+info.getData().get(0).toString());
                Toast.makeText(getApplicationContext(),info.getError(),Toast.LENGTH_LONG).show();
                Log.i("tag", info.getData().get(0).getDepartname());
                Log.i("tag", info.getData().get(1).getDepartname());
            }

            @Override
            public void onFailure(Call<DepartmentInfo> call, Throwable t) {

            }
        });
    }
    private void getQuery()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urls)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IRequestService service = retrofit.create(IRequestService.class);
        Call<DepartmentInfo> call=service.getInfo2("android","5a379b5eed8aaae531df5f60b12100cfb6dff2c1","member","getdepartments");
        call.enqueue(new Callback<DepartmentInfo>() {
            @Override
            public void onResponse(Call<DepartmentInfo> call, Response<DepartmentInfo> response) {
                DepartmentInfo  info=response.body();
                mTextView.setText(info.getError()+"-----"+info.getData().get(0).toString());
                Toast.makeText(getApplicationContext(),info.getError(),Toast.LENGTH_LONG).show();
                Log.i("tag", info.getData().get(0).getDepartname());
                Log.i("tag", info.getData().get(1).getDepartname());
            }

            @Override
            public void onFailure(Call<DepartmentInfo> call, Throwable t) {

            }
        });
    }

    class VisitWebRunnables implements Runnable {
          @Override
          public void run() {
              Retrofit retrofit = new Retrofit.Builder()
                      .baseUrl(urls)
                      .addConverterFactory(GsonConverterFactory.create())
                      .build();
              IRequestService service = retrofit.create(IRequestService.class);
              Call<DepartmentInfo> call=service.getInfoMap(Util.showMap());
              //另一种写法，当然也可以同上面一样
              try {
                  DepartmentInfo info=call.execute().body();
                  resultStr1=info.getError()+"-----"+info.getData().get(0).toString();
                  Log.i("tag", info.getData().get(0).getDepartname());
                  Log.i("tag", info.getData().get(1).getDepartname());
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      };

    private void post()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urls)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IRequestService service = retrofit.create(IRequestService.class);
        Call<DepartmentInfo> call=service.postInfo1();
        call.enqueue(new Callback<DepartmentInfo>() {
            @Override
            public void onResponse(Call<DepartmentInfo> call, Response<DepartmentInfo> response) {
                DepartmentInfo  info=response.body();
                mTextView.setText(info.getError()+"-----"+info.getData().get(0).toString());
                Toast.makeText(getApplicationContext(),info.getError(),Toast.LENGTH_LONG).show();
                Log.i("tag", info.getData().get(0).getDepartname());
                Log.i("tag", info.getData().get(1).getDepartname());
            }

            @Override
            public void onFailure(Call<DepartmentInfo> call, Throwable t) {

            }
        });
    }
    private void postField()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urls)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IRequestService service = retrofit.create(IRequestService.class);
        //两张方式相同
        Call<DepartmentInfo> call=service.postField("android","5a379b5eed8aaae531df5f60b12100cfb6dff2c1","member","getdepartments");
        //Call<DepartmentInfo> call=service.postQuery("android","5a379b5eed8aaae531df5f60b12100cfb6dff2c1","member","getdepartments");
        call.enqueue(new Callback<DepartmentInfo>() {
            @Override
            public void onResponse(Call<DepartmentInfo> call, Response<DepartmentInfo> response) {
                DepartmentInfo  info=response.body();
                mTextView.setText(info.getError()+"-----"+info.getData().get(0).toString());
                Toast.makeText(getApplicationContext(),info.getError(),Toast.LENGTH_LONG).show();
                Log.i("tag", info.getData().get(0).getDepartname());
                Log.i("tag", info.getData().get(1).getDepartname());
            }

            @Override
            public void onFailure(Call<DepartmentInfo> call, Throwable t) {

            }
        });
    }
    private void postMap()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urls)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IRequestService service = retrofit.create(IRequestService.class);
        Call<DepartmentInfo> call=service.postInfoMap(Util.showMap());
        call.enqueue(new Callback<DepartmentInfo>() {
            @Override
            public void onResponse(Call<DepartmentInfo> call, Response<DepartmentInfo> response) {
                DepartmentInfo  info=response.body();
                mTextView.setText(info.getError()+"-----"+info.getData().get(0).toString());
                Toast.makeText(getApplicationContext(),info.getError(),Toast.LENGTH_LONG).show();
                Log.i("tag", info.getData().get(0).getDepartname());
                Log.i("tag", info.getData().get(1).getDepartname());
            }

            @Override
            public void onFailure(Call<DepartmentInfo> call, Throwable t) {

            }
        });
    }



}
