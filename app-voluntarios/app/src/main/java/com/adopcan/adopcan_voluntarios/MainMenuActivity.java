package com.adopcan.adopcan_voluntarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.adopcan.adopcan_voluntarios.DTO.MessageAlert;
import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.R;
import com.adopcan.adopcan_voluntarios.Utils.AlertDialog;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        showAlert(this.getIntent());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }

    /*Si redirecciona muestro un msj*/
    private void showAlert(Intent intent){
        AlertDialog alertDialog = new AlertDialog();
       if ( intent.getExtras()!= null && intent.getExtras().get("message")!= null){
           MessageAlert msg = (MessageAlert) intent.getExtras().get("message");
           alertDialog.showAlertWithAcept(this, msg.getTitle(),msg.getDescription());
       }
    }

    public void listDogs(View view){
        Intent intent = new Intent(this, ListDogs.class);
        startActivity(intent);
    }

    public void reportLostDog(View view){
        Intent intent = new Intent(this, ReportLostDogActivity.class);
        startActivity(intent);
    }

    public void seeLostDog(View view){
        Intent intent = new Intent(this, LostDogMapsActivity.class);
        startActivity(intent);
    }

}
