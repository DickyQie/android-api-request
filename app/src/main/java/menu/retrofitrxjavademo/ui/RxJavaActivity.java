package menu.retrofitrxjavademo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import menu.retrofitrxjavademo.R;
import menu.retrofitrxjavademo.entity.DepartmentInfo;
import menu.retrofitrxjavademo.util.IRxJavaService;
import menu.retrofitrxjavademo.util.Util;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zq on 2016/8/5.
 */

public class RxJavaActivity extends Activity implements View.OnClickListener{

    private TextView mTextView;
    private static String urls = "http://gpj.zhangwoo.cn/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_main);
        initView();
    }

    private void initView()
    {
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.Text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                get();
                break;
            case R.id.btn2:
                postMap();
                break;
            case R.id.btn3:
                showRxjava();
                break;
            case R.id.btn4:
                showJsonObject();
                break;
        }
    }
    private void get()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(urls)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                IRxJavaService service = retrofit.create(IRxJavaService.class);
                Observable<DepartmentInfo> observable=service.postRxjava();
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<DepartmentInfo>() {
                            @Override
                            public void onCompleted() {
                                Toast.makeText(getApplicationContext(),
                                        "请求成功",
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getApplicationContext(),
                                        "请求失败",
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }

                            @Override
                            public void onNext(DepartmentInfo dessertItemCollectionDao) {
                                 mTextView.setText(dessertItemCollectionDao.getError()+"-----"+dessertItemCollectionDao.getData().get(0).toString());
                                Toast.makeText(getApplicationContext(),dessertItemCollectionDao.getError(),Toast.LENGTH_LONG).show();
                                Log.i("tag", dessertItemCollectionDao.getData().get(0).getDepartname());
                                Log.i("tag", dessertItemCollectionDao.getData().get(1).getDepartname());
                            }
                        });
            }
        }).start();

    }

    private void postMap()
    {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(urls)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                final IRxJavaService service = retrofit.create(IRxJavaService.class);
                service.getUserFollowingObservable(Util.showMap())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<DepartmentInfo>() {
                            @Override
                            public void onCompleted() {
                                Toast.makeText(getApplicationContext(),
                                        "请求成功",
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getApplicationContext(),
                                        "onError",
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }

                            @Override
                            public void onNext(DepartmentInfo dessertItemCollectionDao) {
                                mTextView.setText(dessertItemCollectionDao.getError()+"-----"+dessertItemCollectionDao.getData().get(0).toString());
                                Toast.makeText(getApplicationContext(),dessertItemCollectionDao.getError(),Toast.LENGTH_LONG).show();
                                Log.i("tag", dessertItemCollectionDao.getData().get(0).getDepartname());
                                Log.i("tag", dessertItemCollectionDao.getData().get(1).getDepartname());
                            }
                        });
            }}).start();

    }
    Subscription subscription=null;
    private void showRxjava(){
       // RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        Retrofit retrofit11 = new Retrofit.Builder()
                .baseUrl(urls)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        IRxJavaService apiService =  retrofit11.create(IRxJavaService.class);
        Observable<DepartmentInfo> call = apiService.getUser(Util.showMap());
        subscription = call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DepartmentInfo>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onNext(DepartmentInfo user) {
                        Toast.makeText(getApplicationContext(),
                                user.getData().get(0).getDepartname(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }

    public void showJsonObject()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(urls)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                IRxJavaService service = retrofit.create(IRxJavaService.class);
                Observable<JsonObject> observable = service.loadRepo();
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<JsonObject>() {
                            @Override
                            public void onCompleted() {
                                Toast.makeText(getApplicationContext(),
                                        "加载完成",
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getApplicationContext(),
                                        "加载失败",
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }

                            @Override
                            public void onNext(JsonObject dessertItemCollectionDao) {
                                Toast.makeText(getApplicationContext(),
                                        dessertItemCollectionDao.toString(),
                                        Toast.LENGTH_SHORT)
                                        .show();
                                mTextView.setText(dessertItemCollectionDao.toString());
                            }
                        });
            }
        }).start();

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        //使用Rxjava需要在对应的onDestroy方法中调用subscription.unsubscribe()方法，防止OOM
        subscription.unsubscribe();

    }
}
