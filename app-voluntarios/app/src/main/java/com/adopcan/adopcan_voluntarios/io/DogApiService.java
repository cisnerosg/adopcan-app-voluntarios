package com.adopcan.adopcan_voluntarios.io;

import com.adopcan.adopcan_voluntarios.model.Dogs;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DogApiService {
    @GET("datos.php")
    Call <ArrayList <Dogs>> getDogs();
}
