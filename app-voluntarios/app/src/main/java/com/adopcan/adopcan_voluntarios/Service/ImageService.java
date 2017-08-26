package com.adopcan.adopcan_voluntarios.Service;

import android.content.Context;
import android.graphics.Bitmap;

import com.adopcan.adopcan_voluntarios.Utils.HttpMethod;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by german on 23/8/2017.
 */

public class ImageService {

    public StringRequest getImage(String filename, Context context, Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        String url = "http://www.adopcan.com/uploads/" + filename;
        return null;
    }


}
