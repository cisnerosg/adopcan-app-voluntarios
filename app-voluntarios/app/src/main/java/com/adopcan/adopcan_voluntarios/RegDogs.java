package com.adopcan.adopcan_voluntarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RegDogs extends AppCompatActivity {

    TextView dogName;
    TextView dogAge;
    ImageView dogPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_dogs);

        dogName = (TextView)findViewById(R.id.dog_name);
        dogAge = (TextView)findViewById(R.id.dog_age);
        dogPhoto = (ImageView)findViewById(R.id.dog_photo);

        dogName.setText("Wilson");
        dogAge.setText("3");
        dogPhoto.setImageResource(R.drawable.adopcan);

    }
}
