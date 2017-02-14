package com.example.sergio.prom_t3ej31_ficheros;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Activity1 extends AppCompatActivity {

    private static String fichInt = "fichInt.txt";
    private static String fichExt = "fichExt.txt";

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 0;

    Button btnAddIntFile, btnAddExtFile, btnReadIntFile, btnReadExtFile, btnReadResFile,btnDelIntFile, btnDelExtFile;
    EditText txtRead;
    TextView txtWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        //permisos
        int permissionCheck = ContextCompat.checkSelfPermission(Activity1.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(Activity1.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }

        btnAddIntFile = (Button) findViewById(R.id.btn_addIntFile);
        btnAddExtFile = (Button) findViewById(R.id.btn_addExtFile);
        btnReadExtFile = (Button) findViewById(R.id.btn_readExtFile);
        btnReadIntFile = (Button) findViewById(R.id.btn_readIntFile);
        btnReadResFile = (Button) findViewById(R.id.btn_readResFile);
        btnDelExtFile = (Button) findViewById(R.id.btn_delExtFile);
        btnDelIntFile = (Button) findViewById(R.id.btn_delIntFile);

        txtRead = (EditText) findViewById(R.id.editTxt_contentToWrite);
        txtWrite = (TextView) findViewById(R.id.txt_content);

        /*
        INTERNAL FILE
         */
        btnAddIntFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //https://developer.android.com/reference/android/util/Log.html
                //los logs "debug" no se ejecutan en runtime
                Log.d("AddIntFile click","Click en AÑADIR FICH. INT");
                try{
                    OutputStreamWriter osw = new OutputStreamWriter(openFileOutput(fichInt, Context.MODE_PRIVATE));
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
                    BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(fichInt)));
                    String linea = br.readLine();
                    txtWrite.setText(linea);
                } catch (FileNotFoundException e){
                    Log.e("ReadIntFile click","Error al leer del fichero interno. No existe");
                } catch (IOException e){
                    Log.e("ReadIntFile click","Error al leer del fichero interno");
                }
            }
        });

        btnDelIntFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DelIntFile click","Click en BORRAR FICH. INT");
                File dir = getFilesDir();
                File file = new File(dir, fichInt);
                if(file.delete()) {
                    Toast.makeText(Activity1.this, "Fichero borrado", Toast.LENGTH_SHORT).show();
                    Log.d("DelIntFile click","BORRADO");
                }
            }
        });

        /*
        EXTERNAL FILE
         */
        btnAddExtFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //https://developer.android.com/reference/android/util/Log.html
                //los logs "debug" no se ejecutan en runtime
                Log.d("AddExtFile click","Click en AÑADIR FICH. EXT");
                try{
                    File ruta_sd = Environment.getExternalStorageDirectory();
                    File f = new File (ruta_sd.getAbsolutePath(),fichExt);
                    OutputStreamWriter osw =
                            new OutputStreamWriter(new FileOutputStream(f));
                    osw.write(txtRead.getText().toString());
                    osw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btnReadExtFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ReadIntFile click","Click en LEER FICH. INT");
                try{
                    File ruta_sd = Environment.getExternalStorageDirectory();
                    File f= new File(ruta_sd.getAbsolutePath(), fichExt);
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(new FileInputStream(f)));
                    String linea= br.readLine();
                    br.close();
                    txtWrite.setText(linea);
                } catch (FileNotFoundException e){
                    Log.e("ReadExtFile click","Error al leer del fichero externo. No existe");
                } catch (IOException e){
                    Log.e("ReadExtFile click","Error al leer del fichero externo");
                }
            }
        });

        btnDelExtFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DelIntFile click","Click en BORRAR FICH. EXT");

                File ruta_sd = Environment.getExternalStorageDirectory();
                File file = new File (ruta_sd.getAbsolutePath(),fichExt);
                if(file.delete()) {
                    Toast.makeText(Activity1.this, "Fichero borrado", Toast.LENGTH_SHORT).show();
                    Log.d("DelExtFile click","BORRADO");
                }
            }
        });

        /*
        RESOURCE FILE
         */
        btnReadResFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //https://developer.android.com/reference/android/util/Log.html
                //los logs "debug" no se ejecutan en runtime
                Log.d("ReadResFile click","Click en LEER RECURSO");
                try{
                    InputStream fraw = getResources().openRawResource(R.raw.provincias);
                    BufferedReader brin = new BufferedReader( new InputStreamReader(fraw));
                    String linea= brin.readLine();
                    String str = "";
                    while (linea!=null){
                        str += "\n" + linea;
                        linea=brin.readLine();
                    }
                    fraw.close();
                    txtWrite.setText(str);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
