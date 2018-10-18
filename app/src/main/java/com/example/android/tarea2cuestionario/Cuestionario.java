package com.example.android.tarea2cuestionario;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class Cuestionario extends AppCompatActivity {

    // Contador de puntos
    int puntuacion = 0;
    // Lista donde se almancenan las preguntas correctas del usuario
    ArrayList preguntasCorrectas = new ArrayList();
    //Variables para que se pueda realizar el almacenamiento externo
    private static String ARCHIVO = Environment.getExternalStorageDirectory() + "/respuestas.txt";
    private Context context;

    // Variables para las preguntas y respuestas
    RadioGroup radioGroupP1, radioGroupP3, radioGroupP5, radioGroupP8, radioGroupP10;
    RadioButton radioButtonR1P1, radioButtonR4P3, radioButtonR4P5, radioButtonR2P8, radioButtonR3P10;
    EditText editTextRP4, editTextRP7, editTextRP9;
    CheckBox checkBoxR1P2, checkBoxR2P2, checkBoxR3P2, checkBoxR4P2;
    CheckBox checkBoxR1P6, checkBoxR2P6, checkBoxR3P6, checkBoxR4P6;

    // Variables utilizadas para elaborar el archivo de texto
    String p1, r1p1, r2p1, r3p1, r4p1;
    String p2, r1p2, r2p2, r3p2, r4p2;
    String p3, r1p3, r2p3, r3p3, r4p3;
    String p4;
    String p5, r1p5, r2p5, r3p5, r4p5;
    String p6, r1p6, r2p6, r3p6, r4p6;
    String p7;
    String p8, r1p8, r2p8, r3p8, r4p8;
    String p9;
    String p10, r1p10, r2p10, r3p10, r4p10;

    String texto1 = "", texto2Opc1 = "", texto2Opc2 = "", texto2Opc3 = "", texto2Opc4 = "", texto3="",
            texto4="", texto5="", texto6Opc1="", texto6Opc2="", texto6Opc3="", texto6Opc4="",
            texto7="", texto8="", texto9="", texto10="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);

        //Permisos para poder acceder a la memoria externa
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.
                WRITE_EXTERNAL_STORAGE}, 1);

        //Deja de lado FileProvider
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        // Se inicializan todas las variables declaradas anteriormente
        radioGroupP1 = (RadioGroup) findViewById(R.id.rp1_radio_group);
        radioButtonR1P1 = (RadioButton) findViewById(R.id.r1p1_radio_button);
        radioGroupP3 = (RadioGroup) findViewById(R.id.rp3_radio_group);
        radioButtonR4P3 = (RadioButton) findViewById(R.id.r4p3_radio_button);
        radioGroupP5 = (RadioGroup) findViewById(R.id.rp5_radio_group);
        radioButtonR4P5 = (RadioButton) findViewById(R.id.r4p5_radio_button);
        radioGroupP8 = (RadioGroup) findViewById(R.id.rp8_radio_group);
        radioButtonR2P8 = (RadioButton) findViewById(R.id.r2p8_radio_button);
        radioGroupP10 = (RadioGroup) findViewById(R.id.rp10_radio_group);
        radioButtonR3P10 = (RadioButton) findViewById(R.id.r3p10_radio_button);
        editTextRP4 = (EditText) findViewById(R.id.rp4_edit_text);
        editTextRP7 = (EditText) findViewById(R.id.rp7_edit_text);
        editTextRP9 = (EditText) findViewById(R.id.rp9_edit_text);
        checkBoxR1P2 = (CheckBox) findViewById(R.id.r1p2_checkbox);
        checkBoxR2P2 = (CheckBox) findViewById(R.id.r2p2_checkbox);
        checkBoxR3P2 = (CheckBox) findViewById(R.id.r3p2_checkbox);
        checkBoxR4P2 = (CheckBox) findViewById(R.id.r4p2_checkbox);
        checkBoxR1P6 = (CheckBox) findViewById(R.id.r1p6_checkbox);
        checkBoxR2P6 = (CheckBox) findViewById(R.id.r2p6_checkbox);
        checkBoxR3P6 = findViewById(R.id.r3p6_checkbox);
        checkBoxR4P6 = (CheckBox) findViewById(R.id.r4p6_checkbox);

        p1 = getResources().getString(R.string.pregunta1);
        r1p1 = getResources().getString(R.string.respuesta1Pregunta1);
        r2p1 = getResources().getString(R.string.respuesta2Pregunta1);
        r3p1 = getResources().getString(R.string.respuesta3Pregunta1);
        r4p1 = getResources().getString(R.string.respuesta4Pregunta1);
        p2 = getResources().getString(R.string.pregunta2);
        r1p2 = getResources().getString(R.string.respuesta1Pregunta2);
        r2p2 = getResources().getString(R.string.respuesta2Pregunta2);
        r3p2 = getResources().getString(R.string.respuesta3Pregunta2);
        r4p2 = getResources().getString(R.string.respuesta4Pregunta2);
        p3 = getResources().getString(R.string.pregunta3);
        r1p3 = getResources().getString(R.string.respuesta1Pregunta3);
        r2p3 = getResources().getString(R.string.respuesta2Pregunta3);
        r3p3 = getResources().getString(R.string.respuesta3Pregunta3);
        r4p3 = getResources().getString(R.string.respuesta4Pregunta3);
        p4 = getResources().getString(R.string.pregunta4);
        p5 = getResources().getString(R.string.pregunta5);
        r1p5 = getResources().getString(R.string.respuesta1Pregunta5);
        r2p5 = getResources().getString(R.string.respuesta2Pregunta5);
        r3p5 = getResources().getString(R.string.respuesta3Pregunta5);
        r4p5 = getResources().getString(R.string.respuesta4Pregunta5);
        p6 = getResources().getString(R.string.pregunta6);
        r1p6 = getResources().getString(R.string.respuesta1Pregunta6);
        r2p6 = getResources().getString(R.string.respuesta2Pregunta6);
        r3p6 = getResources().getString(R.string.respuesta3Pregunta6);
        r4p6 = getResources().getString(R.string.respuesta4Pregunta6);
        p7 = getResources().getString(R.string.pregunta7);
        p8 = getResources().getString(R.string.pregunta8);
        r1p8 = getResources().getString(R.string.respuesta1Pregunta8);
        r2p8 = getResources().getString(R.string.respuesta2Pregunta8);
        r3p8 = getResources().getString(R.string.respuesta3Pregunta8);
        r4p8 = getResources().getString(R.string.respuesta4Pregunta8);
        p9 = getResources().getString(R.string.pregunta9);
        p10 = getResources().getString(R.string.pregunta10);
        r1p10 = getResources().getString(R.string.respuesta1Pregunta10);
        r2p10 = getResources().getString(R.string.respuesta2Pregunta10);
        r3p10 = getResources().getString(R.string.respuesta3Pregunta10);
        r4p10 = getResources().getString(R.string.respuesta4Pregunta10);
    }

    // Método que permite la validación de las preguntas con sus opciones para RadioButton,
    // CheckBox, EditText para que no existan preguntas sin contestar, si esto ocurre se manda
    // un mensaje para indicarle al usuario que bede contestar todas las preguntas y si el
    // usuario contesto todas las preguntas entonces se procede a realizar los otros métodos
    public void validar(View view) {
        if (radioGroupP1.getCheckedRadioButtonId() == -1
                || radioGroupP3.getCheckedRadioButtonId() == -1
                || radioGroupP5.getCheckedRadioButtonId() == -1
                || radioGroupP8.getCheckedRadioButtonId() == -1
                || radioGroupP10.getCheckedRadioButtonId() == -1
                || editTextRP4.getText().toString().trim().isEmpty()
                || editTextRP7.getText().toString().trim().isEmpty()
                || editTextRP9.getText().toString().trim().isEmpty()
                || (!checkBoxR1P2.isChecked() && !checkBoxR2P2.isChecked()
                && !checkBoxR3P2.isChecked() && !checkBoxR4P2.isChecked())
                || (!checkBoxR1P6.isChecked() && !checkBoxR2P6.isChecked()
                && !checkBoxR3P6.isChecked() && !checkBoxR4P6.isChecked())) {
            String mensaje = "Hay preguntas sin contestar";
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        } else {
            calificar();
            crearArchivo();
            cambiarVentana();
        }
    }

    // Método para abrir otra actividad por medio de Intent, en este caso se esta mandando una
    // varible también que será utilizada en la otro actividad atrevés de putExtra
    public void cambiarVentana() {
        Intent puntaje = new Intent(getApplicationContext(), Puntuacion.class);
        puntaje.putExtra("puntos", puntuacion);
        puntaje.putExtra("preguntasC", preguntasCorrectas);
        startActivity(puntaje);
    }

    // Método para crear un archivo txt que se almaneca en la memoria externa por lo que se necesitan
    // ciertos permisos para acceder a ella, estos son los que se declararon inicialmente, se revisa
    // que la maemoria este disponible para su utilización de lo contrario manda un mensaje de alerta
    public void crearArchivo() {
        //Verificamos el estado de la memoria SD.
        String estadoSD = Environment.getExternalStorageState();
        if (!estadoSD.equals(Environment.MEDIA_MOUNTED)) {
            //Si el estado es diferente a MEDIA_MOUNTED mostrarmos un mensaje.
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setMessage("No se puede escribir en la memoria externa.");
            alert.setPositiveButton(android.R.string.ok, null);
            alert.show();
            return;
        }

        // Se crea el archivo txt ocn FileOutputStream indicandole que se sobreescribira cada que se
        // utilice la aplicación, en e archivo se nuestran las preguntas que se realizaron en el
        // las respuestas que el usuario dio y la respuesta que es la correcta, al igual que los
        // puntos o aciertos de la prueba
        try {
            FileOutputStream fos = new FileOutputStream(ARCHIVO, false);

            String texto = "DETALLES DE CUESTIONARIO \n \n"
                    + "Puntaje: " + puntuacion + "/10 \n \n"
                    + p1 + "\n"
                    + "\t" + "a) " + r1p1 + "\n"
                    + "\t" + "b) " + r2p1 + "\n"
                    + "\t" + "c) " + r3p1 + "\n"
                    + "\t" + "d) " + r4p1 + "\n" + "\n"
                    + "\t" + "TU RESPUESTA: " + texto1 + "\n"
                    + "\t" + "RESPUESTA CORRECTA: " + r1p1 + "\n" + "\n"
                    + p2 + "\n"
                    + "\t" + "a) " + r1p2 + "\n"
                    + "\t" + "b) " + r2p2 + "\n"
                    + "\t" + "c) " + r3p2 + "\n"
                    + "\t" + "d) " + r4p2 + "\n" + "\n"
                    + "\t" + "TU RESPUESTA: " + texto2Opc1 + "\t" + texto2Opc2 + "\t" + texto2Opc3 + "\t" + texto2Opc4 + "\n"
                    + "\t" + "RESPUESTA CORRECTA: " + r2p2 + ", " + r3p2 + "\n" + "\n"
                    + p3 + "\n"
                    + "\t" + "a) " + r1p3 + "\n"
                    + "\t" + "b) " + r2p3 + "\n"
                    + "\t" + "c) " + r3p3 + "\n"
                    + "\t" + "d) " + r4p3 + "\n" + "\n"
                    + "\t" + "TU RESPUESTA: " + texto3 + "\n"
                    + "\t" + "RESPUESTA CORRECTA: " + r4p3 + "\n" + "\n"
                    + p4 + "\n"
                    + "\t" + "TU RESPUESTA: " + texto4 + "\n"
                    + "\t" + "RESPUESTA CORRECTA: Mayor Latencia \n" + "\n"
                    + p5 + "\n"
                    + "\t" + "a) " + r1p5 + "\n"
                    + "\t" + "b) " + r2p5 + "\n"
                    + "\t" + "c) " + r3p5 + "\n"
                    + "\t" + "d) " + r4p5 + "\n" + "\n"
                    + "\t" + "TU RESPUESTA: " + texto5 + "\n"
                    + "\t" + "RESPUESTA CORRECTA: " + r4p5 + "\n" + "\n"
                    + p6 + "\n"
                    + "\t" + "a) " + r1p6 + "\n"
                    + "\t" + "b) " + r2p6 + "\n"
                    + "\t" + "c) " + r3p6 + "\n"
                    + "\t" + "d) " + r4p6 + "\n" + "\n"
                    + "\t" + "TU RESPUESTA: " + texto6Opc1 + "\t" + texto6Opc2 + "\t" + texto6Opc3 + "\t" + texto6Opc4 + "\n"
                    + "\t" + "RESPUESTA CORRECTA: " + r2p6 + ", " + r3p6 + "\n" + "\n"
                    + p7 + "\n"
                    + "\t" + "TU RESPUESTA: " + texto7 + "\n"
                    + "\t" + "RESPUESTA CORRECTA: DSL \n" + "\n"
                    + p8 + "\n"
                    + "\t" + "a) " + r1p8 + "\n"
                    + "\t" + "b) " + r2p8 + "\n"
                    + "\t" + "c) " + r3p8 + "\n"
                    + "\t" + "d) " + r4p8 + "\n" + "\n"
                    + "\t" + "TU RESPUESTA: " + texto8 + "\n"
                    + "\t" + "RESPUESTA CORRECTA: " + r2p8 + "\n" + "\n"
                    + p9 + "\n"
                    + "\t" + "TU RESPUESTA: " + texto9 + "\n"
                    + "\t" + "RESPUESTA CORRECTA: LLQ \n" + "\n"
                    + p10 + "\n"
                    + "\t" + "a) " + r1p10 + "\n"
                    + "\t" + "b) " + r2p10 + "\n"
                    + "\t" + "c) " + r3p10 + "\n"
                    + "\t" + "d) " + r4p10 + "\n" + "\n"
                    + "\t" + "TU RESPUESTA: " + texto10 + "\n"
                    + "\t" + "RESPUESTA CORRECTA: " + r3p10 + "\n" + "\n";
            fos.write(texto.getBytes());
            fos.close();
        } catch (Exception ex) {
            Log.e("Uso ListView", ex.getMessage(), ex);
        }
    }

    // Método que analiza cada una de las preguntas con sus respectivas respuestas para obtener los
    // puntos obtenidos al contestarla y poder dar una puntuación al usuario, por lo que con cada
    // pregunta contestada correctamente se va sumando un punto y también se almacena la lista de
    // las preguntas correctas para mostrarlas al usuario al igual que su puntuación
    public void calificar() {
        // Pregunta 1
        int radioButtonIDP1 = radioGroupP1.getCheckedRadioButtonId();
        RadioButton radioButtonP1 = (RadioButton) radioGroupP1.findViewById(radioButtonIDP1);
        texto1 = (String) radioButtonP1.getText();
        if (radioButtonR1P1.isChecked()) {
            puntuacion += 1;
            preguntasCorrectas.add("Pregunta 1");
        }

        // Pregunta 2
        if (checkBoxR1P2.isChecked()) {
            texto2Opc1 = (String) checkBoxR1P2.getText();
        } else if (checkBoxR2P2.isChecked()) {
            texto2Opc2 = (String) checkBoxR2P2.getText();
        } else if (checkBoxR3P2.isChecked()) {
            texto2Opc3 = (String) checkBoxR3P2.getText();
        } else if (checkBoxR4P2.isChecked()) {
            texto2Opc4 = (String) checkBoxR4P2.getText();
        }
        if (checkBoxR2P2.isChecked() && checkBoxR3P2.isChecked()) {
            puntuacion += 1;
            preguntasCorrectas.add("Pregunta 2");
        }

        // Pregunta 3
        int radioButtonIDP3 = radioGroupP3.getCheckedRadioButtonId();
        RadioButton radioButtonP3 = (RadioButton) radioGroupP3.findViewById(radioButtonIDP3);
        texto3 = (String) radioButtonP3.getText();
        if (radioButtonR4P3.isChecked()) {
            puntuacion += 1;
            preguntasCorrectas.add("Pregunta 3");
        }

        // Pregunta 4
        texto4 = editTextRP4.getText().toString();
        if (editTextRP4.getText().toString().equalsIgnoreCase("mayor latancia")) {
            puntuacion += 1;
            preguntasCorrectas.add("Pregunta 4");
        }

        // Pregunta 5
        int radioButtonIDP5 = radioGroupP5.getCheckedRadioButtonId();
        RadioButton radioButtonP5 = (RadioButton) radioGroupP5.findViewById(radioButtonIDP5);
        texto5 = (String) radioButtonP5.getText();
        if (radioButtonR4P5.isChecked()) {
            puntuacion += 1;
            preguntasCorrectas.add("Pregunta 5");
        }

        // Pregunta 6
        if (checkBoxR1P6.isChecked()) {
            texto6Opc1 = (String) checkBoxR1P6.getText();
        } else if (checkBoxR2P6.isChecked()) {
            texto6Opc2 = (String) checkBoxR2P6.getText();
        } else if (checkBoxR3P6.isChecked()) {
            texto6Opc3 = (String) checkBoxR3P6.getText();
        } else if (checkBoxR4P6.isChecked()) {
            texto6Opc4 = (String) checkBoxR4P6.getText();
        }
        if (checkBoxR2P6.isChecked() && checkBoxR3P6.isChecked()) {
            puntuacion += 1;
            preguntasCorrectas.add("Pregunta 6");
        }

        // Pregunta 7
        texto7 = editTextRP7.getText().toString();
        if (editTextRP7.getText().toString().equalsIgnoreCase("DSL")) {
            puntuacion += 1;
            preguntasCorrectas.add("Pregunta 7");
        }

        // Pregunta 8
        int radioButtonIDP8 = radioGroupP8.getCheckedRadioButtonId();
        RadioButton radioButtonP8 = (RadioButton) radioGroupP8.findViewById(radioButtonIDP8);
        texto8 = (String) radioButtonP8.getText();
        if (radioButtonR2P8.isChecked()) {
            puntuacion += 1;
            preguntasCorrectas.add("Pregunta 8");
        }

        // Pregunta 9
        texto9 = editTextRP9.getText().toString();
        if (editTextRP9.getText().toString().equalsIgnoreCase("LLQ")) {
            puntuacion += 1;
            preguntasCorrectas.add("Pregunta 9");
        }

        // Pregunta 10
        int radioButtonIDP10 = radioGroupP10.getCheckedRadioButtonId();
        RadioButton radioButtonP10 = (RadioButton) radioGroupP10.findViewById(radioButtonIDP10);
        texto10 = (String) radioButtonP10.getText();
        if (radioButtonR3P10.isChecked()) {
            puntuacion += 1;
            preguntasCorrectas.add("Pregunta 10");
        }

    }
}
