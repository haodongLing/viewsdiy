package com.haodong.pracmodule.myretrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * created by linghaoDo on 2020-04-27
 * description:
 * <p>
 * version:
 */
public interface Gank {
    @GET("GanHuo")
    Call<Ganhuo> getGanhuo();

}
