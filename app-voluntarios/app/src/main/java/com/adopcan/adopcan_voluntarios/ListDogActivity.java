package com.adopcan.adopcan_voluntarios;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.adopcan.adopcan_voluntarios.Adapter.DogAdapter;
import com.adopcan.adopcan_voluntarios.Adapter.DogListener;
import com.adopcan.adopcan_voluntarios.ApiWebService.ApiInteractor;
import com.adopcan.adopcan_voluntarios.DTO.Dog;
import com.adopcan.adopcan_voluntarios.R;
import java.util.ArrayList;

public class ListDogActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ApiInteractor mApiInteract;
    private DogAdapter mAdapter;
    private RecyclerView listRecycler;
    private String TAG = ListDogActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dogs);
        //mToolbar = (Toolbar) findViewById(R.id.toolbar);
        listRecycler = (RecyclerView) findViewById(R.id.lista);

        //seteo estilo del toolbar
        /*setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_name);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        getWindow().setBackgroundDrawable(null);
        mToolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_action_name));
        mToolbar.setTitle(getString(R.string.dog_list_title_lbl));*/

        mApiInteract = new ApiInteractor();
        //Obtengo lista de perros
        getDogApi();
    }

    private void getDogApi() {
        mApiInteract.getPerros(new DogListener() {
            @Override
            public void onDogReady(ArrayList<Dog> listDog) {
                //obtuve listado de perros
                fillList(listDog);
            }

            @Override
            public void onError() {
                //ups hubo un error problema de red, parseo,server
                Log.v(TAG, "ERROR");

            }
        });
    }

    //carga de lista
    private void fillList(ArrayList<Dog> list) {
        mAdapter = new DogAdapter(this, list);
        listRecycler.setAdapter(mAdapter);
        listRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listRecycler.setHasFixedSize(false);
        //Cuando clickeo un perro
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dog item = mAdapter.getItemByPos(listRecycler.getChildAdapterPosition(view));
                if (item != null)
                    Log.v(TAG, "name=>" + item.getNombre() + " edad=>" + item.getEdad());
                else
                    Log.d("Error","No hay lista");
            }
        });
    }

}
