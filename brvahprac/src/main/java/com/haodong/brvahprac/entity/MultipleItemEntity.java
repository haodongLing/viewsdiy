package com.haodong.brvahprac.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * @author linghailong
 * @date on 2018/10/31
 * @email 105354999@qq.com
 * @describe : item的数据实体类
 */
public class MultipleItemEntity implements MultiItemEntity {
    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QURUE=new ReferenceQueue<>();
    private final LinkedHashMap<Object,Object> MULTIPLE_FIELDS=new LinkedHashMap<>();
    final SoftReference<LinkedHashMap<Object,Object>> FIELDS_REFERENCE=
            new SoftReference<LinkedHashMap<Object, Object>>(MULTIPLE_FIELDS,ITEM_QURUE);

    public int getItemtype (){
        return (int) FIELDS_REFERENCE.get().get(MultipleFileds.ITEM_TYPE);
    }
    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }

    /**
     *
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public final <T>T getField(Object key){
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    /**
     *
     * @return MULTIPLE_FIELDS
     */
    public final LinkedHashMap<?,?>getFields(){
        return FIELDS_REFERENCE.get();
    }

    public final MultipleItemEntity setField(Object key,Object value){
        FIELDS_REFERENCE.get().put(key,value);
        return this;
    }



    @Override
    public int getItemType() {
        return 0;
    }
}
