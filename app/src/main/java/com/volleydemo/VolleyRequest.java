package com.volleydemo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import util.ApplicationController;
import util.NormalPostRequest;
import util.VolleyUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/****
 *
 * Volley之网络请求
 *
 * 包含两种  get 和   post 请求方式
 *
 * @author zq
 *
 */
public class VolleyRequest extends Activity implements View.OnClickListener{

    private RequestQueue mQueue;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        mQueue = ApplicationController.queue;// Volley.newRequestQueue(this);
        VolleyUtil.initialize(VolleyRequest.this);
        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
        findViewById(R.id.get).setOnClickListener(this);
        findViewById(R.id.post).setOnClickListener(this);
        findViewById(R.id.get1).setOnClickListener(this);
        findViewById(R.id.post1).setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.textView1);

    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.get:
                get();
                break;
            case R.id.get1:
                get1();
                break;
            case R.id.post:
                post();
                break;
            case R.id.post1:
                post1();
                break;
            default:
                break;
        }

    }


    private void get() {
        // 列如
        String url = "https://www.baidu.com";
        mQueue.add(new StringRequest(Method.GET, url, new Listener<String>() {
            @Override
            public void onResponse(String arg0) {
                mTextView
                        .setText("------------------------------------get1-----------------------------------------   \n\n"
                                + arg0);
            }
        }, new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                Toast.makeText(VolleyRequest.this, "Get请求失败", 2).show();
            }

        }));

    }

    private void get1() {

        String url = "https://www.baidu.com";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        mTextView
                                .setText("------------------------------------get2-----------------------------------------   \n\n"
                                        + s);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(VolleyRequest.this, "Get请求失败", 2).show();
            }
        });
        // 设置取消取消http请求标签 Activity的生命周期中的onStiop()中调用
        request.setTag("volleyget");
        ApplicationController.getHttpQueue().add(request);

    }

    public void post() {
        RequestQueue requestQueue = Volley.newRequestQueue(VolleyRequest.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://route.showapi.com/32-9",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mTextView
                                .setText("------------------------------------post1-----------------------------------------   \n\n"
                                        + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyRequest.this, "请求失败", 2).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数

                return showMap();
            }
        };
        requestQueue.add(stringRequest);
    }

    public void post1() {
        RequestQueue requestQueue = ApplicationController.getHttpQueue();
        Request<JSONObject> request = new NormalPostRequest(
                "https://route.showapi.com/32-9",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mTextView
                                .setText("------------------------------------post2---------------------------------------   \n\n"
                                        + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyRequest.this, "请求失败", 2).show();
            }
        }, showMap());
        requestQueue.add(request);
    }

    /***
     *
     * 接口POST参数
     *
     * @return Map
     */

    private Map<String, String> showMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("q", "test");
        map.put("showapi_appid", "11548");
        map.put("showapi_timestamp", "20160511115654");
        map.put("showapi_sign", "bb1d15ab7ce646ec87cc89d684ca4bcb");
        return map;
    }

    @Override
    protected void onStop() {
        super.onStop();
        ApplicationController.getHttpQueue().cancelAll("volleyget"); // 结束Activity时队列里面注销
        mQueue.cancelAll(this);  //取消这个队列里的所有请求：
    }

}
