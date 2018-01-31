package menu.retrofitrxjavademo.util;

import com.google.gson.JsonObject;

import java.util.Map;

import menu.retrofitrxjavademo.entity.DepartmentInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zq on 2016/8/5.
 *
 * 用法和IRequestService的相同
 */

public interface IRxJavaService {

    @GET("http://gpj.zhangwoo.cn/app.php?platform=android&appkey=5a379b5eed8aaae531df5f60b12100cfb6dff2c1&c=member&a=getdepartments")
    Observable<DepartmentInfo> getRxjava();

    @POST("http://gpj.zhangwoo.cn/app.php?platform=android&appkey=5a379b5eed8aaae531df5f60b12100cfb6dff2c1&c=member&a=getdepartments")
    Observable<DepartmentInfo> postRxjava();

    @POST("app.php")
    Observable<DepartmentInfo> getUserFollowingObservable(@QueryMap Map<String,String> map);


    @GET("app.php")
    Observable<DepartmentInfo> getUser(@QueryMap Map<String,String> map);


    @POST("app.php")
    Observable<JsonObject> loadRepo();


}
