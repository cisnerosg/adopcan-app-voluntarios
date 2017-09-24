package com.adopcan.adopcan_voluntarios;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.adopcan.adopcan_voluntarios.DTO.DogTemp;
import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.Utils.Circle;
import com.squareup.picasso.Picasso;

import static com.facebook.FacebookSdk.getApplicationContext;

public class DogDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DogTemp dog = (DogTemp)this.getIntent().getExtras().get("dog");
        loadFicha(dog);

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

    private void loadFicha(DogTemp dog){
        ImageView imageView = (ImageView) findViewById(R.id.imageView5);
        Picasso.with(getApplicationContext()).load("http://www.adopcan.com/uploads/" + dog.getFilename()).transform(new Circle()).into(imageView);
    }
}
