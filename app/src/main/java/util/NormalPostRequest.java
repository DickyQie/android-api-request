package util;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

public class NormalPostRequest extends Request<JSONObject> {
	private Map<String, String> mMap;
	private Listener<JSONObject> mListener;
	public NormalPostRequest(String url, Listener<JSONObject> listener,ErrorListener errorListener, Map<String, String> map) {
	        super(Method.POST, url, errorListener);
	        mListener = listener;
	        mMap = map;
	    }
	  //mMap是已经按照前面的方式,设置了参数的实例
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mMap;
    }
    
	@Override
	protected void deliverResponse(JSONObject response) {
		 mListener.onResponse(response);
		
	}

	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		try {
            String jsonString = new String(response.data,HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
	}

}
