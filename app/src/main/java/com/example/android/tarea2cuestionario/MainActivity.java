package com.example.android.tarea2cuestionario;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int progressStatus = 0;
    ProgressBar progressBar;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // EXTRA: Se agrego un ProgressBar para que muestre la carga del cuestionario, cuando termina de
    // cargarse, se utiliza Intent para ir a otra actividad, en este caso a Cuestionario para comenzar a
    // contestar
    public void cargar(View view) {
        progressBar = (ProgressBar) findViewById(R.id.progress_Bar);

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Modifica el ProgressBar y muestra
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Se duerme por 100 milisegundos.
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent cuestionario = new Intent(getApplicationContext(), Cuestionario.class);
                startActivity(cuestionario);
            }
        }).start();

    }
}
