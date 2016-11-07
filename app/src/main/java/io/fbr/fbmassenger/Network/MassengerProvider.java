package io.fbr.fbmassenger.Network;

import io.fbr.fbmassenger.Utils.ClientConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by farhad on 11/1/16.
 */

public class MassengerProvider {
    private final Retrofit retrofit;
    MassengerService massengerService;

    public MassengerProvider() {
        OkHttpClient okClient=new OkHttpClient();

        retrofit=new Retrofit.Builder()
                .baseUrl(ClientConfig.BASE_URL)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        massengerService=retrofit.create(MassengerService.class);

    }

    public MassengerService getService(){
        return massengerService;
    }

    public Retrofit getRetrofitClient(){
        return retrofit;
    }
}
