package com.volleydemo;

import util.VolleySingleton;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Volley之图片加载
 * Created by zq on 2016/9/17.
 */
public class VolleyImg extends Activity implements View.OnClickListener{

    ImageView imageview1, imageview2;
    NetworkImageView mNetworkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
        initView();

    }

    private void initView() {
        // TODO Auto-generated method stub
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        imageview1 = (ImageView) findViewById(R.id.img1);
        imageview2 = (ImageView) findViewById(R.id.img2);
        mNetworkImageView = (NetworkImageView) findViewById(R.id.networkImageView);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn1:
                img1();
                break;
            case R.id.btn2:
                img2();
                break;
            case R.id.btn3:
                img3();
                break;
            default:
                break;
        }

    }

    private void img1() {
        ImageRequest request = new ImageRequest(
                "http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageview1.setImageBitmap(bitmap);
                    }
                }, 0, 0, Config.RGB_565, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                imageview1.setImageResource(R.mipmap.ic_launcher);//异常时加载的图片
            }
        });
        VolleySingleton.getVolleySingleton(this.getApplicationContext())
                .addToRequestQueue(request);

    }

    public void img2() {
        com.android.volley.toolbox.ImageLoader mImageLoader;
        mImageLoader = VolleySingleton.getVolleySingleton(
                this.getApplicationContext()).getImageLoader();
        // IMAGE_URL是图片网络地址
        // mImageView是ImageView实例
        // R.drawable.def_image默认图片id
        // R.drawable.err_image加载图片错误时的图片
        mImageLoader
                .get("http://hiphotos.baidu.com/baidu/pic/item/63d0f703918fa0ec2ebf584b269759ee3d6ddb7f.jpg",
                        com.android.volley.toolbox.ImageLoader
                                .getImageListener(imageview2,
                                        R.mipmap.ic_launcher,//默认显示的图片
                                        R.mipmap.ic_launcher));//加载异常显示的图片
    }

    /**
     * 使用NetworkImageView加载图片
     */
    public void img3() {
        com.android.volley.toolbox.ImageLoader mImageLoader;
        mImageLoader = VolleySingleton.getVolleySingleton(
                this.getApplicationContext()).getImageLoader();
        mNetworkImageView
                .setImageUrl(
                        "http://hiphotos.baidu.com/baidu/pic/item/f603918fa0ec08fabf7a641659ee3d6d55fbda0d.jpg",
                        mImageLoader);
    }
}
