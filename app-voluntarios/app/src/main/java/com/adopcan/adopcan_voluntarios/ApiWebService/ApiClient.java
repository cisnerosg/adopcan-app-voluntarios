package com.adopcan.adopcan_voluntarios.ApiWebService;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alcides on 18/08/17.
 */

public class ApiClient {

    private static Retrofit retrofit = null;
    private static OkHttpClient client;

    public static OkHttpClient getOKClient() {
        if (client == null) {
            OkHttpClient.Builder clientbuilder = new OkHttpClient.Builder();
            clientbuilder.connectTimeout(7, TimeUnit.SECONDS);
            client = clientbuilder.build();
        }
        return client;
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(getOKClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
