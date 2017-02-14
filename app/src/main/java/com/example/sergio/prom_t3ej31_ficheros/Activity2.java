package com.example.sergio.prom_t3ej31_ficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);



        //cargar el spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        //cargarlo desde archivo
        ArrayList<String> provincias = new ArrayList<String>();
        try{
            InputStream fraw = getResources().openRawResource(R.raw.provincias);
            BufferedReader brin = new BufferedReader(new InputStreamReader(fraw));
            String linea = brin.readLine();
            while (linea != null){
                provincias.add(linea);
                linea = brin.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String[] provinciasArray = new String[provincias.size()];
        for(int i = 0;i<provincias.size();i++){
            provinciasArray[i] = provincias.get(i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,provinciasArray);
        spinner.setAdapter(adapter);
    }
}
