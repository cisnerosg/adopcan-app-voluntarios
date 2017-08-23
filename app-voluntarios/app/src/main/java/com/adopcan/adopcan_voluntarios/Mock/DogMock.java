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
        DogTemp dog = new DogTemp(id, name);

        return dog;
    }


    public List <Dog> getDogs(){

        List<Dog> list = new ArrayList <>();

        list.add(getDog(1,"Morena"));
        list.add(getDog(2,"Nina"));
        list.add(getDog(3,"Popi"));
        list.add(getDog(4,"Black"));

        return list;
    }

}
