package cn.yhq.adapter.expand;

/**
 * Created by Administrator on 2016/5/20.
 */
public interface IChildItemViewProviderSelector<G, C> {
    boolean isForProvider(int groupPosition, G groupEntity, int childPosition, C childEntity);
}
