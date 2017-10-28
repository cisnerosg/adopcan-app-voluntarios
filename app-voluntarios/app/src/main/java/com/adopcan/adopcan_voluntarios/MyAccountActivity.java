package com.adopcan.adopcan_voluntarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.adopcan.adopcan_voluntarios.DTO.Account;
import com.adopcan.adopcan_voluntarios.Security.SecurityHandler;

public class MyAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        fillAccount();
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

    private void fillAccount(){

        Account account = SecurityHandler.getSecurity().getUser().getAccount();

        TextView name = (TextView) findViewById(R.id.textView_name);
        name.setText(account.getName());

        TextView lastname = (TextView) findViewById(R.id.textView_lastname);
        lastname.setText(account.getLastname());

        TextView email = (TextView) findViewById(R.id.textView_email);
        email.setText(account.getEmail());


    }

}
