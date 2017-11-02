package com.adopcan.adopcan_voluntarios;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.adopcan.adopcan_voluntarios.CustomHttpRequest.AppController;
import com.adopcan.adopcan_voluntarios.DTO.DogTemp;
import com.adopcan.adopcan_voluntarios.Security.SecurityHandler;
import com.adopcan.adopcan_voluntarios.Service.DogService;
import com.adopcan.adopcan_voluntarios.Utils.Circle;
import com.adopcan.adopcan_voluntarios.Utils.DateUtils;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TabsDogsActivity extends AppCompatActivity  implements  Response.ErrorListener, Response.Listener<String> {

    private DogService dogService;
    private List<DogTemp> dogs;
    private List<DogTemp> dogsFavorites;
    private DateUtils dateUtils;
    private String selectedTab = TAB1;

    private static final String TAB1 = "Todos";
    private static final String TAB2 = "Favoritos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tabs_dogs);
        Resources res = getResources();

        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec(TAB1);
        spec.setContent(R.id.tab1);
        spec.setIndicator("Todos", null);
        tabs.addTab(spec);

        spec=tabs.newTabSpec(TAB2);
        spec.setContent(R.id.tab2);
        spec.setIndicator("Favoritos", null);
        tabs.addTab(spec);

        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                selectedTab = tabId;

                boolean favorite = false;
                if(selectedTab == TAB2){
                    favorite = true;
                }

                initListDog(favorite);
            }
        });

        tabs.setCurrentTab(0);
        dateUtils = new DateUtils();
        initListDog(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                Log.i("ActionBar", "Atrás!");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*busca los perros y los agrega en el listview*/
    private void initListDog(boolean favorites){
        dogService = new DogService();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(SecurityHandler.getSecurity().getUser()!= null) {

            TextView filter = (TextView) findViewById(R.id.editText_filter);

            Request<?> request = dogService.getDogs(favorites,filter.getText().toString(),this, this);
            AppController.getInstance().addToRequestQueue(request);
        }else{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dogs.size();
        }

        @Override
        public Object getItem(int i) {
            return dogs.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.dog_list_info, null);

            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            TextView textName = (TextView)view.findViewById(R.id.textView_name);

            Picasso.with(getApplicationContext()).load("http://www.adopcan.com/uploads/" + dogs.get(i).getFilename()).transform(new Circle()).into(imageView);

            textName.setText(dogs.get(i).getName());
            Button button = (Button)view.findViewById(R.id.button_more);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), DogDetailActivity.class);
                    intent.putExtra ("dog", dogs.get(i));
                    startActivity(intent);                }
            });

            final ImageView image_fav = (ImageView) view.findViewById(R.id.imageView_fav);

            if(dogs.get(i).isFavorite()) {
                image_fav.setImageResource(R.drawable.starton);
            }else{
                image_fav.setImageResource(R.drawable.startoff);
            }

            image_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dogs.get(i).isFavorite()) {
                    changeFavoriteDog(0,dogs.get(i).getId());
                    image_fav.setImageResource(R.drawable.startoff);

                    Toast.makeText(getApplicationContext(), dogs.get(i).getName() +" dejo de estar entre tus favoritos",
                            Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(getApplicationContext(), dogs.get(i).getName() +" ahora esta entre tus favoritos",
                            Toast.LENGTH_SHORT).show();

                    changeFavoriteDog(1,dogs.get(i).getId());
                    image_fav.setImageResource(R.drawable.starton);
                }
                }

            });

            return view;
        }
    }

    private void changeFavoriteDog(int favorite, String id){
        Request<?> request =dogService.changeFavoriteDog(favorite, id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(selectedTab.equals(TAB1)){
                    initListDog(false);
                }else{
                    initListDog(true);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }

    @Override
    public void onResponse(String response) {

        try {

            ListView listView = null;
            dogs= getListFromJson(response);

            if(selectedTab.equals(TAB1)){
                listView = (ListView)findViewById(R.id.listView_dogs);
            }else if(selectedTab.equals(TAB2)){
                listView = (ListView)findViewById(R.id.listView_favorites);
            }

            TabsDogsActivity.CustomAdapter customAdapter = new TabsDogsActivity.CustomAdapter();
            listView.setAdapter(customAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void cleanFavoritesDogs(){
        List<DogTemp> aux = new ArrayList<>();

        for(DogTemp dog : dogs){
            if(dog.isFavorite()){
                aux.add(dog);
            }
        }

        dogs = aux;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        if (error == null || error.networkResponse == null) {
            return;
        }

        String body;
        //get status code here
        final String statusCode = String.valueOf(error.networkResponse.statusCode);
        //get response body and parse with appropriate encoding
        try {
            body = new String(error.networkResponse.data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            // exception
        }

    }


    private static final List<DogTemp> getListFromJson(String json) throws Exception {
        Gson gson = new GsonBuilder().create();
        Type typeOfList = new TypeToken<List<DogTemp>>(){}.getType();
        return gson.fromJson(json, typeOfList);
    }

    public void searchWithFilter(View view){
        if(selectedTab.equals(TAB1)){
            initListDog(false);
        }else if(selectedTab.equals(TAB2)) {
            initListDog(true);
        }
    }
    public void cleanFilter(View view){
        TextView filter = (TextView) findViewById(R.id.editText_filter);
        filter.setText("");
    }

}
