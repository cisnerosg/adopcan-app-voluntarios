package com.adopcan.adopcan_voluntarios;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.adopcan.adopcan_voluntarios.DTO.MessageAlert;
import com.adopcan.adopcan_voluntarios.DTO.OrganizationTemp;
import com.adopcan.adopcan_voluntarios.DTO.User;
import com.adopcan.adopcan_voluntarios.DTO.UserType;
import com.adopcan.adopcan_voluntarios.Security.SecurityHandler;
import com.adopcan.adopcan_voluntarios.Utils.AlertDialog;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;

import retrofit2.http.HEAD;

import static android.R.id.message;


public class SolapaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        showAlert(this.getIntent());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solapa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        customMenu();
    }

    private void customMenu()
    {

        boolean isVoluntary = !SecurityHandler.getSecurity().getUser().getAccount().getUserType().equals(UserType.SIMPLE); //Si tiene security y si no está logueado con facebook

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();

        if(!isVoluntary) {
            nav_Menu.findItem(R.id.nav_calendar).setVisible(false);
            nav_Menu.findItem(R.id.nav_organization).setVisible(false);
            nav_Menu.findItem(R.id.nav_dogs2).setVisible(false);
            nav_Menu.findItem(R.id.nav_calendar).setVisible(false);
        }
    }

    /*Si redirecciona muestro un msj*/
    private void showAlert(Intent intent){
        AlertDialog alertDialog = new AlertDialog();
        if ( intent.getExtras()!= null && intent.getExtras().get("message")!= null){
            MessageAlert msg = (MessageAlert) intent.getExtras().get("message");
            alertDialog.showAlertWithAcept(this, msg.getTitle(),msg.getDescription());
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.solapa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(SecurityHandler.getSecurity().getUser()!= null) {

            if (id == R.id.nav_calendar) {
                Intent intent = new Intent(this, CalendarActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_organization) {
                Intent intent = new Intent(this, OrganizationActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_dogs2) {
                Intent intent = new Intent(this, TabsDogsActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_report) {
                Intent intent = new Intent(this, ReportLostDogActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_map) {
                Intent intent = new Intent(this, LostDogMapsActivity.class);
                startActivity(intent);


            } else if (id == R.id.nav_account) {
                Intent intent = new Intent(this, MyAccountActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_close_session) {
                this.finish();
                LoginManager.getInstance().logOut();
                SecurityHandler.getSecurity().cleanSesion();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        }else{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void redirectAdopcan(){
        Intent viewIntent =
                new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.google.com/"));
        startActivity(viewIntent);
    }
}
