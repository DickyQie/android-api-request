package util;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

/**
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
