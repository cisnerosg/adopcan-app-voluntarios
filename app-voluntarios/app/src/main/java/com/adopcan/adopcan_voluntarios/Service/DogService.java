package com.adopcan.adopcan_voluntarios.Service;

import android.content.Intent;

import com.adopcan.adopcan_voluntarios.DTO.Dog;
import com.adopcan.adopcan_voluntarios.DTO.DogTemp;
import com.adopcan.adopcan_voluntarios.DTO.OrganizationTemp;
import com.adopcan.adopcan_voluntarios.ListDogActivity;
import com.adopcan.adopcan_voluntarios.Mock.DogMock;
import com.adopcan.adopcan_voluntarios.Security.SecurityHandler;
import com.adopcan.adopcan_voluntarios.SolapaActivity;
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

    public StringRequest getDogs(boolean favorite,Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        List<Dog> list = new ArrayList<>();

        OrganizationTemp org = SecurityHandler.getSecurity().getUser().getOrganization();
        String favoriteParam = "";

        if(favorite){
            favoriteParam = "favoritos=1";
        }
        String url = "http://www.adopcan.com/api/"+ org.getId() +"/animales?" + favoriteParam;

        HttpMethod httpMethod = new HttpMethod();
        return httpMethod.httpGetMethod(url,responseListener, errorListener);

    }

}
