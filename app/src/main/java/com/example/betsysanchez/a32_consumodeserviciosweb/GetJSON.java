package com.example.betsysanchez.a32_consumodeserviciosweb;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by BetsySanchez on 20/03/2018.
 */

public class GetJSON extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            URL url = new URL("http://www.apilayer.net/api/live?access_key=2de1974596cc2fb3660423f5c0d33b69&format=1");
            HttpURLConnection httpURLConnection = (HttpURLConnection)
                    url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
                JSONObject principal=new JSONObject(data);
                JSONObject quotes=new JSONObject(principal.getString("quotes"));

                dataParsed="Valor del Dolar en:"+"\n"+
                        "Euros :"+quotes.getString("USDEUR")+"\n"+
                        "Pesos :"+quotes.getString("USDMXN")+"\n"+
                        "Franco Ruandes: :"+quotes.getString("USDRWF");

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.txt.setText(this.dataParsed);
    }


}
