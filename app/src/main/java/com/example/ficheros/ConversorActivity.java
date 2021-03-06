package com.example.ficheros;

import android.app.ProgressDialog;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.Header;


public class ConversorActivity extends AppCompatActivity implements View.OnClickListener {

    //Instanciamos los objetos de editText, un radio button(para comprobar el tipo de cambio) y el boton (para el metodo onClick)
    Button boton;
    RadioButton rboton;
    EditText edText;
    EditText edText2;
    String cambio;
    final String url = "http://alumno.mobi/~alumno/superior/benitez/cambio.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor);
        boton =(Button) findViewById(R.id.botonConvertir);
        boton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        leerCambio();
        rboton=(RadioButton) findViewById(R.id.rbEaD);
        if (rboton.isChecked())
        {
            edText = (EditText) findViewById(R.id.etDolar);
            edText2 = (EditText) findViewById(R.id.etEuro);
            try {
                edText.setText(convertirADolares(edText2.getText().toString(),cambio));
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }else
        {
            edText = (EditText) findViewById(R.id.etEuro);
            edText2 = (EditText) findViewById(R.id.etDolar);

            try {
                edText.setText(convertirAEuros(String.format(edText2.getText().toString()),cambio));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public String convertirADolares(String cantidad,String cambio) {
        double valor =
                Double.parseDouble(cantidad) / Double.parseDouble(cambio);
        return
                Double.toString(valor);
    }

    public String convertirAEuros(String cantidad,String cambio) {
        double valor =
                Double.parseDouble(cantidad) * Double.parseDouble(cambio);
        return
                Double.toString(valor);
    }

    public void leerCambio(){
        File miFichero = new File (Environment.getExternalStorageDirectory().getAbsolutePath());
        RestClient.get(url, new FileAsyncHttpResponseHandler(miFichero) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(getApplicationContext(), "Fallo: " + statusCode + "\n" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                String cadena=null;
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    BufferedReader in = new BufferedReader(new InputStreamReader(fis));
                    while ((cadena = in.readLine()) != null) {
                        cambio=cadena;
                    }
                    in.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}