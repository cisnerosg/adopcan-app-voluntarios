package com.adopcan.adopcan_voluntarios.DTO;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by german on 24/8/2017.
 */

public class TagReport {

    private Report report;
    private Context context;
    private Bitmap image;

    public TagReport(Report report, Context context) {
        this.report = report;
        this.context = context;
    }

    public Report getReport() {
        return report;
    }

    public Context getContext() {
        return context;
    }

}
