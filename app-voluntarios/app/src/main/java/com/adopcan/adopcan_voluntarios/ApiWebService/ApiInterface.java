package com.adopcan.adopcan_voluntarios.ApiWebService;

import com.adopcan.adopcan_voluntarios.DTO.Dog;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Alcides on 18/08/17.
 */
public interface ApiInterface {
    @GET("/tp/datos.php")
    Call<ArrayList<Dog>> getDog();
}
