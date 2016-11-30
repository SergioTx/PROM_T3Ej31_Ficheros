package com.example.sergio.prom_t3ej31_ficheros;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Activity1 extends AppCompatActivity {

    Button btnAddIntFile, btnAddExtFile, btnReadIntFile, btnReadExtFile, btnReadResFile,btnDelIntFile, btnDelExtFile;
    EditText txtRead;
    TextView txtWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        btnAddIntFile = (Button) findViewById(R.id.btn_addIntFile);
        btnAddExtFile = (Button) findViewById(R.id.btn_addExtFile);
        btnReadExtFile = (Button) findViewById(R.id.btn_readExtFile);
        btnReadIntFile = (Button) findViewById(R.id.btn_readIntFile);
        btnReadResFile = (Button) findViewById(R.id.btn_readResFile);
        btnDelExtFile = (Button) findViewById(R.id.btn_delExtFile);
        btnDelIntFile = (Button) findViewById(R.id.btn_delIntFile);

        txtRead = (EditText) findViewById(R.id.editTxt_contentToWrite);
        txtWrite = (TextView) findViewById(R.id.txt_content);

        btnAddIntFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //https://developer.android.com/reference/android/util/Log.html
                //los logs "debug" no se ejecutan en runtime
                Log.d("AddIntFile click","Click en AÑADIR FICH. INT");
                try{
                    OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("fichInt.txt", Context.MODE_PRIVATE));
                    String str = txtRead.getText().toString();
                    osw.write(str);
                    osw.close();
                } catch (Exception e){
                    Log.e("AddIntFile click","Error al escribir en fichero interno");
                }
            }
        });

        btnReadIntFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ReadIntFile click","Click en LEER FICH. INT");
                try{
                    BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("fichInt.txt")));
                    String linea = br.readLine();
                    txtWrite.setText(linea);
                } catch (FileNotFoundException e){
                    Log.e("ReadIntFile click","Error al leer del fichero interno. No existe");
                } catch (IOException e){
                    Log.e("ReadIntFile click","Error al leer del fichero interno");
                }
            }
        });
    }
}
