package com.adopcan.adopcan_voluntarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.Service.ReportService;

public class ReportLostDogDetailsActivity extends AppCompatActivity {

    private Report report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        report = (Report)this.getIntent().getExtras().get("report");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_lost_dog_details);

    }

    private Report getReportWithDescription(String description){
        report.setDescription(description);

        return report;
    }

    public void sendReport(View view){
       String description = ((EditText)findViewById(R.id.editText_description)).toString();
        ReportService service = new ReportService();
        Report report = getReportWithDescription(description);

        service.sendReport(report);
    }
}
