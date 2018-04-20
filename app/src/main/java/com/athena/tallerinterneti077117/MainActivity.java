package com.athena.tallerinterneti077117;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.athena.tallerinterneti077117.Models.Person;
import com.athena.tallerinterneti077117.Parser.JsonPerson;
import com.athena.tallerinterneti077117.URL.HttpManager;

import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;
    List<Person> personList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        textView = (TextView) findViewById(R.id.textView);

        Task tarea = new Task();
        tarea.execute("http://pastoral.iucesmag.edu.co/practica/listar.php");

    }

    public Boolean isOnLine(){
        //Obtener el servicio de la conectividad en android
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //Obtener la información del estado de la red
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null){
            return true;
        }else{
            return false;
        }
    }


    // Método para procesar los datos
    //public void processData(String s){
    //textView.setText( "Numero: "+s);
    //textView.setTextSize(Integer.parseInt(s));
    //    textView.append(s+ "\n");
    //}

    public void processData(){
        /*Toast.makeText(this,String.valueOf(postList.size()),Toast.LENGTH_SHORT).show();
        for (Post str : postList){
            textView.append(str.toString() + "\n");
        }*/
        for (Person per : personList){
            textView.append((per.getNombre() + "\n"));
        }
    }

    public class Task  extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManager.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return  content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                personList = JsonPerson.getDataJson(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            processData();
        }
    }
}
