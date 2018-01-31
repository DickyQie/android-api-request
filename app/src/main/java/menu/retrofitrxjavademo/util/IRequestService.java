package menu.retrofitrxjavademo.util;

import java.util.Map;

import menu.retrofitrxjavademo.entity.DepartmentInfo;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by zq on 2016/8/5.
 */

public interface IRequestService {



    @GET("http://gpj.zhangwoo.cn/app.php?platform=android&appkey=5a379b5eed8aaae531df5f60b12100cfb6dff2c1&c=member&a=getdepartments")
    Call<DepartmentInfo> getInfo1();

    /*@Query("apikey") String apikey 用于接口键值对类型参数*/
    @GET("app.php")
    Call<DepartmentInfo> getInfo2(@Query("platform") String platform, @Query("appkey") String appkey, @Query("c") String ip, @Query("a") String tag);

    /*@QueryMap  如果Query参数比较多，那么可以通过@QueryMap方式将所有的参数集成在一个Map统一传递*/
    @GET("app.php")
    Call<DepartmentInfo> getInfoMap(@QueryMap Map<String,String> map);

    @POST("http://gpj.zhangwoo.cn/app.php?platform=android&appkey=5a379b5eed8aaae531df5f60b12100cfb6dff2c1&c=member&a=getdepartments")
    Call<DepartmentInfo> postInfo1();

    @FormUrlEncoded
    @POST("app.php")
    Call<DepartmentInfo> postField(@Field("platform") String bookId, @Field("appkey") String title,
                                     @Field("c") String content, @Field("a") String rating);
    @POST("app.php")
    Call<DepartmentInfo> postQuery(@Query("platform") String bookId, @Query("appkey") String title,
                                     @Query("c") String content, @Query("a") String rating);
    @FormUrlEncoded
    @POST("app.php")
    Call<DepartmentInfo> postInfoMap(@FieldMap Map<String,String> map);


}
