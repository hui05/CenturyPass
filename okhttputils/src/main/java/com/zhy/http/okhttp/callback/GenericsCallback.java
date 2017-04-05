package com.zhy.http.okhttp.callback;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Response;

/**
 * Created by JimGong on 2016/6/23.
 */

public abstract class GenericsCallback<T> extends Callback<T> {
    IGenericsSerializator mGenericsSerializator;

    public GenericsCallback(IGenericsSerializator serializator) {
        mGenericsSerializator = serializator;
    }

    public GenericsCallback() {
        mGenericsSerializator = new IGenericsSerializator() {
            @Override
            public <T> T transform(String response, Class<T> classOfT) {
                T t = new Gson().fromJson(response, classOfT);
                return t;
            }
        };
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws IOException {
        String string = response.body().string();
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (entityClass == String.class) {
            return (T) string;
        }
        T bean = mGenericsSerializator.transform(string, entityClass);
        return bean;
    }

}
