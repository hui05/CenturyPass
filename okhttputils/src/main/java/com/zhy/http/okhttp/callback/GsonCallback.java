package com.zhy.http.okhttp.callback;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.Response;

/**
 * Created by hui on 17/03/08.
 */
public abstract class GsonCallback<T extends Object> extends Callback {
    @Override
    public T parseNetworkResponse(Response response, int id) throws IOException {
        String json = response.body().string();
//        T t = new Gson().fromJson(json, T.get);
        return null;
    }
}
