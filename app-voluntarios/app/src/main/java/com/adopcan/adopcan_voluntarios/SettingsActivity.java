package com.adopcan.adopcan_voluntarios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.adopcan.adopcan_voluntarios.DTO.MessageAlert;
import com.adopcan.adopcan_voluntarios.Utils.AlertDialog;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void logOut(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}
