package com.adopcan.adopcan_voluntarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.adopcan.adopcan_voluntarios.CustomHttpRequest.AppController;
import com.adopcan.adopcan_voluntarios.DTO.CalendarInfo;
import com.adopcan.adopcan_voluntarios.DTO.DogTemp;
import com.adopcan.adopcan_voluntarios.Service.CalendarInfoService;
import com.adopcan.adopcan_voluntarios.Service.DogService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DogsActivity extends AppCompatActivity implements  Response.ErrorListener, Response.Listener<String>{

    private DogService dogService;
    private List<DogTemp> dogs;
    private DateUtils dateUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_dogs);
        dateUtils = new DateUtils();
        initListDog();

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
    private void initListDog(){
        dogService = new DogService();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Request<?> request = dogService.getDogs(this, this);
        AppController.getInstance().addToRequestQueue(request);

    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dogs.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.dog_list_info, null);

            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            TextView textName = (TextView)view.findViewById(R.id.textView_name);
//            TextView textSexo = (TextView)view.findViewById(R.id.textView);
//            TextView textEdad = (TextView)view.findViewById(R.id.textView);
//            TextView textEstado = (TextView)view.findViewById(R.id.textViewEstado);


            Picasso.with(getApplicationContext()).load("http://www.adopcan.com/uploads/prueba_599cbe20e44d1.png").resize(500, 500).into(imageView);
            textName.setText(dogs.get(i).getName());
//            textSexo.setText(dogs.get(i).getSex());
//            textEstado.setText(dogs.get(i).getState());
//            textEdad.setText(dogs.get(i).getEdad());

            return view;
        }
    }

    @Override
    public void onResponse(String response) {

        try {
            dogs= getListFromJson(response);
            ListView listView = (ListView)findViewById(R.id.listView_dogs);
            CustomAdapter customAdapter = new CustomAdapter();
            listView.setAdapter(customAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

}
