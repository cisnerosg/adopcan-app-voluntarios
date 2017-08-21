package com.adopcan.adopcan_voluntarios.Service;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by german on 20/8/2017.
 */

public class CustomHttpPostRequest extends AsyncTask<String, Integer, String> {
    @Override
    protected String doInBackground(String... params) {
        BufferedReader in = null;
        String baseUrl = params[0];
        String jsonData = params[1];

        try {
            //Creamos un objeto Cliente HTTP para manejar la peticion al servidor
            HttpClient httpClient = new DefaultHttpClient();
            //Creamos objeto para armar peticion de tipo HTTP POST
            HttpPost post = new HttpPost(baseUrl);

            //Configuramos los parametos que vaos a enviar con la peticion HTTP POST
            List<NameValuePair> nvp = new ArrayList<NameValuePair>(2);
            nvp.add(new BasicNameValuePair("article", jsonData));
            //post.setHeader("Content-type", "application/json");
            StringEntity se = new StringEntity(jsonData);
            post.setEntity(new UrlEncodedFormEntity(nvp));

            //Se ejecuta el envio de la peticion y se espera la respuesta de la misma.
            HttpResponse response = httpClient.execute(post);
            Log.w("pedido token", response.getStatusLine().toString());

            //Obtengo el contenido de la respuesta en formato InputStream Buffer y la paso a formato String
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            return sb.toString();

        } catch (Exception e) {
            return "Exception happened: " + e.getMessage();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void onProgressUpdate(Integer... progress) {
        //Se obtiene el progreso de la peticion
        Log.w("accesso token","Indicador de pregreso " + progress[0].toString());
    }

    protected void onPostExecute(String result) {
        //Se obtiene el resultado de la peticion Asincrona
        Log.w("accesso token","Resultado obtenido " + result);
        processResult(result);
    }

    public void processResult(String result) {
        if (result.contains("OK")) {
            Log.w("accesso token","todo bien " + result);
        } else {
            Log.w("accesso token","todo mal " + result);
        }
    }
}

