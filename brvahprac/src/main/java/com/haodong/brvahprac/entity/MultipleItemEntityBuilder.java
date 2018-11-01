package com.haodong.brvahprac.entity;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * @author linghailong
 * @date on 2018/10/31
 * @email 105354999@qq.com
 * @describe :
 */
public class MultipleItemEntityBuilder {
    public static final LinkedHashMap<Object,Object>FIELDS=new LinkedHashMap<>();

    public MultipleItemEntityBuilder() {
        FIELDS.clear();
    }
    public final MultipleItemEntityBuilder setItemType(int itemType){
        FIELDS.put(MultipleFileds.ITEM_TYPE,itemType);
        return this;
    }
    public final MultipleItemEntityBuilder setField(Object key,Object value){
        FIELDS.put(key,value);
        return this;
    }
    public final MultipleItemEntityBuilder setFields(WeakHashMap<Object,Object>hashMap){
        FIELDS.putAll(hashMap);
        return this;
    }
    public final MultipleItemEntity build(){
        return new MultipleItemEntity(FIELDS);
    }





}
