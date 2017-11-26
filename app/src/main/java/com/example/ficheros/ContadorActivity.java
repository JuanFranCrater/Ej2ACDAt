package com.example.ficheros;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

public class ContadorActivity extends AppCompatActivity {
    int alarma1Min;
    String alarma1Men;
    int alarma2Min;
    String alarma2Men;
    int alarma3Min;
    String alarma3Men;
    int alarma4Min;
    String alarma4Men;
    int alarma5Min;
    String alarma5Men;
    int contadorAlarmas=0;
    TextView mTextField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);
        Bundle bnd =getIntent().getExtras().getBundle("alarmas");
        mTextField= (TextView)findViewById(R.id.txvContador);
        mTextField.setText("00");
        alarma1Min=bnd.getInt("Alarma1");
        alarma1Men=bnd.getString("Alarma1Men");
        alarma2Min=bnd.getInt("Alarma2");
        alarma2Men=bnd.getString("Alarma2Men");
        alarma3Min=bnd.getInt("Alarma3");
        alarma3Men=bnd.getString("Alarma3Men");
        alarma4Min=bnd.getInt("Alarma4");
        alarma4Men=bnd.getString("Alarma4Men");
        alarma5Min=bnd.getInt("Alarma5");
        alarma5Men=bnd.getString("Alarma5Men");
        IniciarContadores();

    }
    public void IniciarContadores()
    {
        if(contadorAlarmas<5) {

            switch (contadorAlarmas) {
                case 0:
                    if(alarma1Min!=0){
                    IniciarContador(alarma1Min,alarma1Men);
                    }else{contadorAlarmas++;
                        IniciarContadores();}
                    break;
                case 1:
                    if(alarma2Min!=0) {
                        IniciarContador(alarma2Min, alarma2Men);
                    }else{contadorAlarmas++;
                        IniciarContadores();}
                    break;
                case 2:
                    if(alarma3Min!=0){
                    IniciarContador(alarma3Min,alarma3Men);
                    }else{contadorAlarmas++;
                        IniciarContadores();
                        }
                    break;
                case 3:
                    if(alarma4Min!=0){
                    IniciarContador(alarma4Min,alarma4Men);
                    }else{contadorAlarmas++;
                        IniciarContadores();}
                    break;
                case 4:
                    if(alarma5Min!=0){
                    IniciarContador(alarma5Min,alarma5Men);
                    }else{contadorAlarmas++;
                        IniciarContadores();}
                    break;
                default:
                    contadorAlarmas++;
                    IniciarContadores();
                    break;
            }
        }else{
            startActivity(new Intent(ContadorActivity.this,AlarmaActivity.class));
        }
    }

    public void IniciarContador(int minutes, final String message)
    {
        new CountDownTimer(minutes*60*1000, 1000) {

            public void onTick(long millisUntilFinished) {

                mTextField.setText("Segundos hasta siguiente alarma: "+ "\n" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Toast.makeText(ContadorActivity.this,message,Toast.LENGTH_SHORT).show();
                contadorAlarmas++;
                IniciarContadores();
            }
        }.start();
    }
}
