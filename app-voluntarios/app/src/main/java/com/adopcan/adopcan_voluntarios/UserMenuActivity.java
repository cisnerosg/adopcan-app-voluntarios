package com.adopcan.adopcan_voluntarios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import com.adopcan.adopcan_voluntarios.DTO.MessageAlert;
import com.adopcan.adopcan_voluntarios.R;
import com.adopcan.adopcan_voluntarios.Utils.AlertDialog;
import com.facebook.login.LoginManager;

public class UserMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        showAlert(this.getIntent());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*Si redirecciona muestro un msj*/
    private void showAlert(Intent intent){
        AlertDialog alertDialog = new AlertDialog();
       if ( intent.getExtras()!= null && intent.getExtras().get("message")!= null){
           MessageAlert msg = (MessageAlert) intent.getExtras().get("message");
           alertDialog.showAlertWithAcept(this, msg.getTitle(),msg.getDescription());
       }
    }

    public void reportLostDog(View view){
        Intent intent = new Intent(this, ReportLostDogActivity.class);
        startActivity(intent);
    }

    public void seeLostDog(View view){
        Intent intent = new Intent(this, LostDogMapsActivity.class);
        startActivity(intent);
    }

    public void logOut(View view){
        this.finish();
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
