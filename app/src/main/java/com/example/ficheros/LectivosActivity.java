package com.example.ficheros;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Date;
import java.util.Calendar;


public class LectivosActivity extends AppCompatActivity {
Calendar calendar;
DatePickerDialog.OnDateSetListener datePD;
Button btn;
Button btn2;
    Button btn3;
EditText edtFechaInicio;
EditText edtFechaFin;
    DatePickerDialog dpdFI;
    DatePickerDialog dpdFF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectivos);
        edtFechaInicio= (EditText)findViewById(R.id.edtFechaInicio);
        edtFechaFin= (EditText)findViewById(R.id.edtFechaFin);
        calendar = Calendar.getInstance();
        btn=(Button)findViewById(R.id.btnFechaInicio);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpdFI = new DatePickerDialog(LectivosActivity.this,datePD, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dpdFI.show();
            }
        });
        btn2=(Button)findViewById(R.id.btnFechaFinal);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpdFF = new DatePickerDialog(LectivosActivity.this,datePD, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dpdFF.show();
            }
        });
        btn3=(Button)findViewById(R.id.btnCalcularDias);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtFechaInicio.setText(String.valueOf(dpdFI.getDatePicker().getDayOfMonth())+"/"+String.valueOf(dpdFI.getDatePicker().getMonth())+"/"+String.valueOf(dpdFI.getDatePicker().getYear()));

            }
        });
    }
}
