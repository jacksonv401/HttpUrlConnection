package com.example.jackson.httpurlconnection;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HomeScreen extends AppCompatActivity {
    Button btnget,btnpost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        btnget = (Button) findViewById(R.id.btnget);
        btnpost = (Button) findViewById(R.id.btnpost);

        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new GetRequest().execute();
            }
        });

        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new PostExecute().execute();
                new URLencodedPost().execute();
            }
        });
    }
}
 class GetRequest extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... params) {

        try {
            /*URL url = new URL("http://services.groupkt.com/country/get/all");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            Log.e("","");*/

            URL url = new URL("https://reqres.in/api/users?page=2");
            HttpURLConnection con =(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            InputStream is = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            BufferedReader br = new BufferedReader(isr);
            String line = "";
            StringBuffer buffer = new StringBuffer();

            while ((line = br.readLine())!= null){
                buffer.append(line);
            }
            line = buffer.toString();

            JSONObject jsonObject = new JSONObject(line);
            JSONArray jsonArray = jsonObject.optJSONArray("data");
            if(con.getResponseCode() == 200){

            }
            Log.e("","");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}

class PostExecute extends AsyncTask<String ,String, String>{

    @Override
    protected String doInBackground(String... params) {

        try {
            JSONObject param = new JSONObject();
            param.put("name","morpheus");
            param.put("job","zion resident");

            URL url = new URL("https://reqres.in/api/users");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(osw);
            bufferedWriter.write(String.valueOf(param));

            int a =con.getResponseCode();
            Log.e("","");

            String line = "";
            StringBuffer stringBuffer =new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((line = bufferedReader.readLine())!= null){
                stringBuffer.append(line);
            }
            line = stringBuffer.toString();
            Log.e("","");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
class URLencodedPost extends AsyncTask<Void,Void,Void>{

    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url = new URL("https://reqres.in/api/users");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("name", "Jackson")
                    .appendQueryParameter("job", "Android Developer");
            String query = builder.build().getEncodedQuery();

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();
            int reponseStatus = conn.getResponseCode();
            String responseMessage = conn.getResponseMessage();
            Log.d("","");
            String line = "";
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            while ((line = bufferedReader.readLine())!= null){
                stringBuffer.append(line);
            }
            Log.d("","");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
