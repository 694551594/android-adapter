package cn.yhq.adapter.core;

/**
 * Created by Administrator on 2016/10/1.
 */

public class ItemData {
    private Object data;

    public ItemData(Object data) {
        this.data = data;
    }

    public <T> T getData() {
        return (T) data;
    }

    public boolean isInstance(Object data) {
        return this.data.getClass() == data.getClass();
    }
}
