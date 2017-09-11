package com.adopcan.adopcan_voluntarios.Service;

import com.adopcan.adopcan_voluntarios.DTO.Dog;
import com.adopcan.adopcan_voluntarios.DTO.DogTemp;
import com.adopcan.adopcan_voluntarios.ListDogActivity;
import com.adopcan.adopcan_voluntarios.Mock.DogMock;
import com.adopcan.adopcan_voluntarios.Utils.HttpMethod;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by german on 16/8/2017.
 */

public class DogService {

    public List<DogTemp> getListDog(){
        List<DogTemp> dogs = new ArrayList<DogTemp>();

        DogMock mock = new DogMock();
        dogs = mock.getDogs();

        return dogs;
    }

    public StringRequest getDogs(Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        List<Dog> list = new ArrayList<>();
        String url = "http://www.adopcan.com/api/e93b4e5d-b617-40ee-97c7-2e67ac33843b/animales";

        HttpMethod httpMethod = new HttpMethod();
        return httpMethod.httpGetMethod(url,responseListener, errorListener);
    }

}
