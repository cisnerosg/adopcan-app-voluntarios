package com.adopcan.adopcan_voluntarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.adopcan.adopcan_voluntarios.DTO.MessageAlert;
import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.Service.ReportService;

public class ReportLostDogDetailsActivity extends AppCompatActivity {

    private Report report;
    private com.adopcan.adopcan_voluntarios.Utils.AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        report = (Report)this.getIntent().getExtras().get("report");
        alertDialog = new com.adopcan.adopcan_voluntarios.Utils.AlertDialog();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_lost_dog_details);

    }

    private Report getReportWithDescription(String description){
        report.setDescription(description);

        return report;
    }

    public void sendReport(View view){
       String description = ((EditText)findViewById(R.id.editText_description)).getText().toString();

        if(description.equals("")){
            alertDialog.showAlertWithAcept(this, "Alerta", "Tenés que agregar un comentanrio, acordate que mientras mas describas más fácil lo podrán reconocer");
        }else {
            ReportService service = new ReportService();
            Report report = getReportWithDescription(description);
            service.sendReport(report);
            login();
        }

    }

    private void login(){
        Intent intent = new Intent(this, MainMenuActivity.class);
        MessageAlert msg = new MessageAlert("Gracias!!","Hemos recibido tu reporte, con tu ayuda muchos perros podrán encontrar un hogar");
        intent.putExtra ("message", msg);
        startActivity(intent);
    }



}
