package com.example.ficheros;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.File;
import java.io.FileNotFoundException;

import cz.msebera.android.httpclient.entity.mime.Header;

public class UploadsActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtRuta;
    private Button btnSubir;
    private Button btnExplorar;
    private File file;
    private static final int ABRIRFICHERO_REQUEST_CODE = 0;
    private static final String URL_TO_POST = "http://alumno.mobi/~alumno/superior/benitez/";
    private static final int MAX_TIMEOUT = 2000;
    private static final int RETRIES = 1;
    private static final int TIMEOUT_BETWEEN_RETRIES = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploads);
        edtRuta = (EditText) findViewById(R.id.edtRuta);
        btnSubir = (Button) findViewById(R.id.btnSubir);
        btnSubir.setOnClickListener(this);
        btnExplorar = (Button) findViewById(R.id.btnExplorar);
        btnExplorar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnSubir:
                subir();
                break;
            case R.id.btnExplorar:
                explorar();
                break;

        }
    }
    private void subir() {
        final ProgressDialog progreso = new ProgressDialog(UploadsActivity.this);
        final AsyncHttpClient cliente = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        cliente.setTimeout(MAX_TIMEOUT);
        cliente.setMaxRetriesAndTimeout(RETRIES, TIMEOUT_BETWEEN_RETRIES);
        if (!edtRuta.getText().toString().trim().isEmpty()) {
            file = new File(edtRuta.getText().toString());
            if (file.length() < 100000) {
                try {
                    params.put("fileToUpload", file);

                    cliente.post(URL_TO_POST, params, new TextHttpResponseHandler() {

                        @Override
                        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                            progreso.dismiss();
                            Toast.makeText(UploadsActivity.this, "No se ha podido subir el fichero. Error: " + statusCode, Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString) {
                            progreso.dismiss();
                            Toast.makeText(UploadsActivity.this, "Fichero subido", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onStart() {
                            super.onStart();
                            progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progreso.setMessage("Subiendo fichero...");
                            progreso.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialogInterface) {
                                    cliente.cancelAllRequests(true);
                                }
                            });
                            progreso.show();
                        }
                    });
                } catch (FileNotFoundException e) {
                    Toast.makeText(UploadsActivity.this, "No se ha encontrado el fichero", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(UploadsActivity.this, "El archivo debe tener un tamaño menor a 1MB", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void explorar() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        String[] extraMIMETypes = {"image/jpeg", "image/png", "text/html", "text/plain", "audio/mpeg3", "application/pdf"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, extraMIMETypes);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, ABRIRFICHERO_REQUEST_CODE);
        else {
            //informar que no hay ninguna aplicación para manejar ficheros
            Toast.makeText(this, "No hay aplicación para manejar ficheros", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                edtRuta.setText(data.getData().getPath());
            }
        }
    }
}

