package com.adopcan.adopcan_voluntarios.Adapter;

import com.adopcan.adopcan_voluntarios.DTO.Dog;
import java.util.ArrayList;

/**
 * Created by Alcides on 18/08/17.
 */

public interface DogListener {
    void onDogReady(ArrayList<Dog> listDog);
    void onError();

}







