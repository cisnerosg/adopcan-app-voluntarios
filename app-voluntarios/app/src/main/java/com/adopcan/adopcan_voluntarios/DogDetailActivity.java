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
import com.adopcan.adopcan_voluntarios.Utils.DateUtils;
import com.squareup.picasso.Picasso;

import java.util.Date;

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

        TextView textName = (TextView) findViewById(R.id.textView2);
        TextView textSexo = (TextView) findViewById(R.id.text_sexo);
        TextView textEdad = (TextView) findViewById(R.id.text_edad);
        TextView textFecha = (TextView) findViewById(R.id.text_fecha);
        TextView textColor = (TextView) findViewById(R.id.text_color);
        TextView textDieta = (TextView) findViewById(R.id.text_dieta);

        textName.setText(dog.getName());
        String sexo = "Sexo: ";
        if(dog.getSex() != "1"){
            sexo += "Hembra";
        } else {
            sexo += "Macho";
        }
        textSexo.setText(sexo);

        String fechaIngreso = "Fecha Ingreso: ";
        if(dog.getAdmissionDate()== null){
            fechaIngreso += "No registrada";
        }else{
            DateUtils dateUtils = new DateUtils();
            Date date = dateUtils.getDateByString(dog.getAdmissionDate());
            fechaIngreso += dateUtils.getDate(date);
        }
        textFecha.setText(fechaIngreso);

        String edad = "Edad: ";
        if(dog.getEdad()== null){
            edad += "No registrada";
        }else{
            edad += dog.getEdad();
        }
        textEdad.setText(edad);

        String diet = "Dieta: ";
        if(dog.getDiet()== null){
            diet += "No registrada";
        }else{
            diet += dog.getDiet();
        }
        textDieta.setText(diet);

        String color = "Color: ";
        if(dog.getColor()== null){
            color += "No registrada";
        }else{
            color += dog.getColor();
        }
        textColor.setText(color);

    }
}
