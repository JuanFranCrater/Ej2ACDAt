package com.example.ficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AlarmaActivity extends AppCompatActivity implements View.OnClickListener {


    EditText edtMin1;
    EditText edtMen1;
    EditText edtMin2;
    EditText edtMen2;
    EditText edtMin3;
    EditText edtMen3;
    EditText edtMin4;
    EditText edtMen4;
    EditText edtMin5;
    EditText edtMen5;
    Button btnIniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);
        inicializar();
    }
    void inicializar() {
        edtMin1 = (EditText) findViewById(R.id.edtMinAl1);
        edtMen1 = (EditText) findViewById(R.id.edtMenAl1);
        edtMin2 = (EditText) findViewById(R.id.edtMinAl2);
        edtMen2 = (EditText) findViewById(R.id.edtMenAl2);
        edtMin3 = (EditText) findViewById(R.id.edtMinAl3);
        edtMen3 = (EditText) findViewById(R.id.edtMenAl3);
        edtMin4 = (EditText) findViewById(R.id.edtMinAl4);
        edtMen4 = (EditText) findViewById(R.id.edtMenAl4);
        edtMin5 = (EditText) findViewById(R.id.edtMinAl5);
        edtMen5 = (EditText) findViewById(R.id.edtMenAl5);
        btnIniciar = (Button)findViewById(R.id.btnInicio);
        btnIniciar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view ==btnIniciar)
        {

        }
    }
}
