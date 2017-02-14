import com.google.gson.Gson;
import com.xiaolan.bean.ItemSimpleMsg;

import java.util.ArrayList;

/**
 * Author: fallen
 * Date: 17-2-13
 * Time: 下午5:40
 * Usage:
 */
public class test {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        ItemSimpleMsg msg=new ItemSimpleMsg();
        Gson gson = new Gson();
        String ss = gson.toJson(list);
        msg.setThumbnail(ss);
        System.out.println(gson.toJson(msg));
    }
}
