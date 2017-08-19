package com.adopcan.adopcan_voluntarios.ApiWebService;

import com.adopcan.adopcan_voluntarios.DTO.Dog;
import com.adopcan.adopcan_voluntarios.Adapter.DogListener;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiInteractor {
    private ApiInterface mApiService;

    public ApiInteractor() {
        mApiService = ApiClient.getClient().create(ApiInterface.class);
    }

    public void getPerros(final DogListener callbck) {
        Call<ArrayList<Dog>> call = mApiService.getDog();
        if (call != null) {
            call.enqueue(new Callback<ArrayList<Dog>>() {
                             @Override
                             public void onResponse(Call<ArrayList<Dog>> call, Response<ArrayList<Dog>> response) {
                                 callbck.onDogReady(response.body());
                             }

                             @Override
                             public void onFailure(Call<ArrayList<Dog>> call, Throwable t) {
                                 //reporto que hubo un error
                                 //El tipo de error se puede saber viendo Throwable instancia de que tipo de error es
                                 callbck.onError();
                             }
                         }
            );
        }
    }


}
