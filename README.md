# Volley框架之网络请求和图片加载

 <p>Volley是 Google 推出的 Android 异步网络请求框架和图片加载框架。<span style="color:#000000"><strong>Volley的特性</strong></span>的请求队列，一定程度符合 Http 规范，包括请求头的处理，缓存机制的支持等。<br> (3).自定义的网络图像加载视图（NetworkImageView，ImageLoader等)&nbsp;。<br> (4). 提供简便的图片加载工具。</p> 
<p>本案例包含get，post请求和几种网络图片加载的方式，效果如图：</p> 
<p>&nbsp;&nbsp;&nbsp; <img alt="" height="343" src="https://static.oschina.net/uploads/space/2017/0209/094556_Szys_2945455.gif" width="267"></p> 
<p>辅助类&nbsp;ApplicationController.java</p> 
<pre><code class="language-java">/**
 * 
 * 
 * 使用请求队列
 * Volley的所有请求都放在一个队列，然后进行处理，这里是你如何将创建一个请求队列
 * @author Administrator
 *
 */
public class ApplicationController extends Application {
	
	 public static RequestQueue queue;

	    @Override
	    public void onCreate() {
	        super.onCreate();
	        queue = Volley.newRequestQueue(getApplicationContext());//使用全局上下文
	    }

	    public static RequestQueue getHttpQueue() {
	        return queue;
	    } 

}
</code></pre> 
<pre><code class="language-java">/****
 * 
 * Volley之网络请求
 * 
 * 包含两种  get 和   post 请求方式
 * 
 * @author Administrator
 * 
 */
public class VolleyRequest extends Activity implements OnClickListener {

	private RequestQueue mQueue;
	TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
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
		mQueue.add(new StringRequest(Method.GET, url, new Listener&lt;String&gt;() {
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
				new Response.Listener&lt;String&gt;() {
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
				new Response.Listener&lt;String&gt;() {
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
			protected Map&lt;String, String&gt; getParams() {
				// 在这里设置需要post的参数

				return showMap();
			}
		};
		requestQueue.add(stringRequest);
	}

	public void post1() {
		RequestQueue requestQueue = ApplicationController.getHttpQueue();
		Request&lt;JSONObject&gt; request = new NormalPostRequest(
				"https://route.showapi.com/32-9",
				new Response.Listener&lt;JSONObject&gt;() {
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

	private Map&lt;String, String&gt; showMap() {
		Map&lt;String, String&gt; map = new HashMap&lt;String, String&gt;();
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

}</code></pre> 
<p>图片加载：</p> 
<pre><code class="language-java">/****
 * 
 * Volley之图片加载
 * 
 * @author Administrator
 *
 */
public class VolleyImg extends Activity implements OnClickListener {

	ImageView imageview1,imageview2;
	NetworkImageView mNetworkImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
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
		ImageRequest request = new ImageRequest("http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",
			    new Response.Listener&lt;Bitmap&gt;() {
			        @Override
			        public void onResponse(Bitmap bitmap) {
			        	imageview1.setImageBitmap(bitmap);
			        }
			    }, 0, 0, Config.RGB_565,
			    new Response.ErrorListener() {
			        public void onErrorResponse(VolleyError error) {
			        	imageview1.setImageResource(R.drawable.ic_launcher);
			        }
			    });
			    VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);

	}

	public void img2() {
		com.android.volley.toolbox.ImageLoader mImageLoader;
		mImageLoader = VolleySingleton.getVolleySingleton(this.getApplicationContext()).getImageLoader();
		//IMAGE_URL是图片网络地址
		//mImageView是ImageView实例
		//R.drawable.def_image默认图片id
		//R.drawable.err_image加载图片错误时的图片
		mImageLoader.get("http://hiphotos.baidu.com/baidu/pic/item/63d0f703918fa0ec2ebf584b269759ee3d6ddb7f.jpg", 
		com.android.volley.toolbox.ImageLoader.getImageListener(imageview2,R.drawable.ic_launcher, R.drawable.ic_launcher));
	}


	/**
	 * 使用NetworkImageView加载图片
	 */
	public void img3()
	{
		com.android.volley.toolbox.ImageLoader mImageLoader;
		mImageLoader = VolleySingleton.getVolleySingleton(this.getApplicationContext()).getImageLoader();
		mNetworkImageView.setImageUrl("http://hiphotos.baidu.com/baidu/pic/item/f603918fa0ec08fabf7a641659ee3d6d55fbda0d.jpg", mImageLoader);
	}	

}
</code></pre> 
<p>记得AndroidManifest.xml&nbsp;中加</p> 
<pre><code class="language-html">  &lt;uses-permission android:name="android.permission.INTERNET"/&gt;
  &lt;uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/&gt;
  &lt;application android:name="util.ApplicationController"&gt;</code></pre> 
