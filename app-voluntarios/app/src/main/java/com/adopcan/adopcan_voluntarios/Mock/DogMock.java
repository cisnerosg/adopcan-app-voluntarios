package com.adopcan.adopcan_voluntarios.Mock;

import com.adopcan.adopcan_voluntarios.DTO.Dog;
import com.adopcan.adopcan_voluntarios.DTO.DogTemp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by german on 16/8/2017.
 */

public class DogMock {

    public DogTemp getDog(long id, String name){

        return null;
    }


    public List <DogTemp> getDogs(){

        List<DogTemp> list = new ArrayList <DogTemp>();

        DogTemp dog = new DogTemp("12","horacio");
        list.add(dog);

        dog = new DogTemp("13","hmaduro");
        list.add(dog);

        dog = new DogTemp("14","rogelio");
        list.add(dog);

        return list;
    }

}
