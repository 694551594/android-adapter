package cn.yhq.adapter.expand;


public interface IChildItemViewProviderKeyPolicy<G, C> {
  int getItemViewTypeKey(int groupPosition, G groupEntity, int childPosition, C childEntity);
}
