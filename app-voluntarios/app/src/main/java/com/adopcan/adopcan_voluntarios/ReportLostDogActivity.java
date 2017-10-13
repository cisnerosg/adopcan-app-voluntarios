package com.adopcan.adopcan_voluntarios;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.R;
import com.adopcan.adopcan_voluntarios.Utils.SaveImage;
import com.adopcan.adopcan_voluntarios.Volley.AppHelper;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ReportLostDogActivity extends AppCompatActivity {

    private ImageButton captureBtn;
    private ImageView capture;
    private final static int CAPTURE_IMAGE = 0;
    private Bitmap bitmap;
    private String filename;
    private static final int SELECT_FILE = 1;
    private com.adopcan.adopcan_voluntarios.Utils.AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        alertDialog = new com.adopcan.adopcan_voluntarios.Utils.AlertDialog();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_lost_dog);



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

    public void openCamera(View view) {

        if (Build.VERSION.SDK_INT >= 23) {
            int permissionCheckWrite = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int permissionCheckCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

            List<String> permissions = new ArrayList<>();

            if (permissionCheckWrite != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if (permissionCheckCamera != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.CAMERA);
            }

            if(!permissions.isEmpty()) {
                String[] array = permissions.toArray(new String[0]);
                ActivityCompat.requestPermissions(this, array , 1);
            }else{
                startCamera();
            }
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        for(int i=0; i<grantResults.length; i++){
            if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                return;
            }
        }
        startCamera();

    }

    private void startCamera(){
        captureBtn = (ImageButton) findViewById(R.id.captureButton);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAPTURE_IMAGE);
    }

    public void openGalery(View v) {

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);

        startActivityForResult(
                Intent.createChooser(intent, "Selecciona una imagen"),
                SELECT_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SELECT_FILE:
                    Uri selectedImage = data.getData();
                    InputStream is;
                    try {
                        is = getContentResolver().openInputStream(selectedImage);
                        BufferedInputStream bis = new BufferedInputStream(is);
                        Bitmap bitmap2 = BitmapFactory.decodeStream(bis);
                        capture = (ImageView) findViewById(R.id.imagePreview);
                        capture.setImageBitmap(bitmap2);
                    } catch (FileNotFoundException e) {
                        Log.d("tag",e.getMessage());
                    }
                case CAPTURE_IMAGE:
                    Bundle bundle = data.getExtras();
                    bitmap = (Bitmap) bundle.get("data");
                    capture = (ImageView) findViewById(R.id.imagePreview);
                    capture.setImageBitmap(bitmap);
                    saveImage(bitmap);
            }
        }
    }

    private void saveImage(Bitmap image){
        SaveImage savefile = new SaveImage();
        filename = savefile.SaveImage(this, bitmap);
    }

    private Report getReportWithFilenamePhoto(){
        Report report = new Report();

        byte[] bytes = AppHelper.getFileDataFromDrawable(bitmap);

        report.setPhotoByte(bytes);

        return report;
    }

    public void openReportMap(View view){

        if(bitmap == null){
            alertDialog.showAlertWithAcept(this, "Alerta", "Tenés que tener una foto para continuar");
        }else{

            Intent intent = new Intent(this, ReportMapsActivity.class);
            intent.putExtra ("report", getReportWithFilenamePhoto());
            startActivity(intent);
        }
    }

}
