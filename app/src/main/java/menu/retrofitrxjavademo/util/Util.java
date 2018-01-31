package menu.retrofitrxjavademo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zq on 2016/8/5.
 */

public class Util {

    public  static  final Map<String,String> showMap()
    {
        Map<String,String> map=new HashMap<String, String>();
        map.put("platform","android");
        map.put("appkey","5a379b5eed8aaae531df5f60b12100cfb6dff2c1");
        map.put("c","member");
        map.put("a","getdepartments");
        return map;

    }

}
