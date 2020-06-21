package com.haodong.pracmodule.myretrofit;

import com.example.haodong.common.util.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * created by linghaoDo on 2020-04-27
 * description:
 * <p>
 * version:
 */
public class ApiManager {
    public interface ApiCallback<T> {
        void onSuccess(T t);

        void onFail(Throwable throwable);
    }

    public <T> Callback<T> getDefaultCallback(final ApiCallback<T> callback) {
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                handleResponse(response, callback);

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                handleFailure(t, callback);

            }
        };

    }


    public <T> void handleResponse(Response<T> response, ApiCallback<T> callback) {
        if (response.isSuccessful() && response.body() != null) {
            callback.onSuccess(response.body());
        } else {
            String errorMessage = "";
            try {
                errorMessage = response.errorBody().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            LogUtil.e("errorMessage-->" + errorMessage);

        }
    }

    public <T> void handleFailure(Throwable t, ApiCallback<T> callback) {
        LogUtil.e("response failure : " + t.getMessage());
        callback.onFail(t);
    }


}
