package cn.yhq.adapter.app;

import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */

public class MutipleTypeGroupObject {
    public String text;
    public List<MutipleTypeChildObject> childs;
    public int type = TYPE_1;


    public final static int TYPE_1 = 0;
    public final static int TYPE_2 = 1;
}
