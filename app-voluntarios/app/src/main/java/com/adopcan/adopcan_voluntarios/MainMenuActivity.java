package com.adopcan.adopcan_voluntarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.adopcan.adopcan_voluntarios.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }


    public void reportLostDog(View view){
        Intent intent = new Intent(this, ReportLostDogActivity.class);
        startActivity(intent);
    }

    public void listDogs(View view){
        Intent intent = new Intent(this, ListDogs.class);
        startActivity(intent);
    }
}
