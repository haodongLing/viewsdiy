package com.haodong.practice.retrofit;

/**
 * describe :
 * date on 2019/6/24
 * author linghailong
 * email 105354999@qq.com
 */
public interface ParameterHandler<T> {
    public void apply(T value);
    // 很多策略，query queryMap,Part,Field。。。
    class Query<T> implements ParameterHandler<T>{
        private String key;// 保存的参数的key 如：userName ,password;

        public Query(String key) {
            this.key = key;
        }

        @Override
        public void apply(T value) {

        }
    }

}
