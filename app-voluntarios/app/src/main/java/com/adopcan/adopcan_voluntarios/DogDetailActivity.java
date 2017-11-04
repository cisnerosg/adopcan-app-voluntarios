package com.adopcan.adopcan_voluntarios;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

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

        TextView textName = (TextView) findViewById(R.id.textView_name);
        TextView textSexo = (TextView) findViewById(R.id.text_sexo);
        TextView textEdad = (TextView) findViewById(R.id.text_edad);
        TextView textFecha = (TextView) findViewById(R.id.text_fecha);
        TextView textColor = (TextView) findViewById(R.id.text_color);
        TextView textDieta = (TextView) findViewById(R.id.text_dieta);

        textName.setText(dog.getName());
        if(dog.getSex() != "1"){
            textSexo.setText("Hembra");
        } else {
            textSexo.setText("Macho");
        }
        textFecha.setText(dog.getAdmissionDate());
        textEdad.setText(dog.getEdad());
        textDieta.setText(dog.getDiet());
        textColor.setText(dog.getColor());

    }
}
