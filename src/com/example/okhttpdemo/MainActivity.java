package com.example.okhttpdemo;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

/****
 * 
 * OkHttp实现get ,post 图片加载 图片上传 自定义回调接口
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends Activity implements OnClickListener {
	TextView _mTextView;
	String url = "https://route.showapi.com/32-9";
	ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();

	}

	private void initView() {
		findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);
		findViewById(R.id.button3).setOnClickListener(this);
		findViewById(R.id.button4).setOnClickListener(this);
		findViewById(R.id.button5).setOnClickListener(this);
		_mTextView = (TextView) findViewById(R.id.textView1);
		mImageView = (ImageView) findViewById(R.id.imageView1);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			getHttp();
			break;
		case R.id.button2:
			postHttp();
			break;
		case R.id.button3:
			getImage(v);
			break;
		case R.id.button4:
			postFile();
			break;
		case R.id.button5:
			showCall();
			break;
		default:
			break;
		}

	}

	/****
	 * get
	 * 
	 * @param view
	 */
	public void getHttp() {
		OkHttpUtils.get().url("http://www.baidu.com").id(100).build()
				.execute(new MyStringCallback());
	}

	/**
	 * post
	 * 
	 * @param view
	 */
	public void postHttp() {
		OkHttpUtils.post().url(url).addParams("q", "test")
				.addParams("showapi_appid", "11548")
				.addParams("showapi_timestamp", "201601012135954")
				.addParams("showapi_sign", "bb1d15ab7ce646ec87cc89d684ca4bcb")
				.build()//
				.execute(new MyStringCallback());

	}

	/***
	 * 
	 * 
	 * 提交一个Gson字符串到服务器端，注意：传递JSON的时候，不要通过addHeader去设置contentType，
	 * 而使用.mediaType(MediaType.parse("application/json; charset=utf-8")).。
	 * 
	 * @param view
	 */
	public void postString(View view) {
		OkHttpUtils.postString().url(url)
				.content(new Gson().toJson(new Bean("zhy", "123")))
				.mediaType(MediaType.parse("application/json; charset=utf-8"))
				.build().execute(new MyStringCallback());
	}

	/***
	 * 
	 * post表单形式上传多个文件
	 * 
	 * @param view
	 */
	public void postFile() {
		String path4 = "/storage/emulated/legacy/Tencent/MobileQQ/qbiz/html5/2312/sqimg.qq.com/"
				+ "qq_product_operations/dyzx_folder/images/bg.jpg";
		File file4 = new File(path4);
		Map<String, String> params = new HashMap<String, String>();
		params.put("key", "value");
		params.put("key", "value");
		OkHttpUtils.post().addFile("filedata0", "bg.jpg", file4)
				.addFile("filedata1", "bg.jpg", file4).url(url).params(params)//
				.build()//
				.execute(new MyStringCallback());
	}

	/**
	 * 
	 * 提交单个文件
	 */
	public void getFile() {
		// TODO Auto-generated method stub
		String path = "/storage/emulated/legacy/Tencent/MobileQQ/qbiz/html5/2312/sqimg.qq.com/"
				+ "qq_product_operations/dyzx_folder/images/bg.jpg";
		File file = new File(path);
		OkHttpUtils.postFile().url(url).file(file).build()
				.execute(new MyStringCallback());
	}

	/***
	 * 加载图片
	 * 
	 * @param view
	 */
	public void getImage(View view) {
		String url = "http://avatar.csdn.net/8/6/0/1_dickyqie.jpg";
		OkHttpUtils.get().url(url).tag(this).build().connTimeOut(20000)
				.readTimeOut(20000).writeTimeOut(20000)
				.execute(new BitmapCallback() {
					@Override
					public void onError(Call call, Exception e, int id) {
						_mTextView.setText("onError:" + e.getMessage());
					}

					@Override
					public void onResponse(Bitmap bitmap, int id) {
						mImageView.setImageBitmap(bitmap);
					}
				});
	}

	public class MyStringCallback extends StringCallback {
		@Override
		public void onBefore(Request request, int id) {
			setTitle("loading...");
		}

		@Override
		public void onAfter(int id) {
			setTitle("OkHttpDemo");
		}

		@Override
		public void onError(Call call, Exception e, int id) {
			e.printStackTrace();
			_mTextView.setText("onError:" + e.getMessage());
		}

		@Override
		public void onResponse(String response, int id) {
			Log.e("cc", "onResponse：complete");
			_mTextView.setText("onResponse:" + response);

			switch (id) {
			case 100:
				Toast.makeText(MainActivity.this, "http", Toast.LENGTH_SHORT)
						.show();
				break;
			case 101:
				Toast.makeText(MainActivity.this, "https", Toast.LENGTH_SHORT)
						.show();
				break;
			}
		}

		@Override
		public void inProgress(float progress, long total, int id) {
			Log.e("s", "inProgress:" + progress);

		}
	}

	/****
	 * 
	 * 自定义回调函数实现
	 * 
	 * @author Administrator
	 * 
	 */

	public class UserCallback extends StringCallback {

		@Override
		public void onError(Call arg0, Exception arg1, int arg2) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, arg0.toString() + "",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onResponse(String arg0, int arg1) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, arg0.toString() + "",
					Toast.LENGTH_SHORT).show();
		}

	}

	private void showCall() {

		OkHttpUtils.post().url(url).addParams("q", "test")
				.addParams("showapi_appid", "11548")
				.addParams("showapi_timestamp", "201601012175954")
				.addParams("showapi_sign", "bb1d15ab7ce646ec87cc89d684ca4bcb")
				.build().execute(new UserCallback());

	}

}
