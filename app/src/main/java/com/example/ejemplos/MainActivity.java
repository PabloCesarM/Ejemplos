package com.example.ejemplos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText campoUsuario, campoPass;
    TextView txtUsuario, txtPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


            campoUsuario=findViewById(R.id.editTextUser);
            campoPass=findViewById(R.id.editTextPassword);
            txtPass=findViewById(R.id.textViewPass);
            txtUsuario=findViewById(R.id.textViewUser);


            //llamar a la función cargar preferencias
            cargarPreferencias();
        });
    }




    /*// SHARED PREFERENCES
    public  void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonGuardar:
                guardarPreferencias();
                break;
            case R.id.buttonCargar:
                Intent intent=new Intent(this,ConsultaPrefencias.class);
                startActivity(intent);
                break;
        }
    }*/

    private void cargarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("credenciales", MODE_PRIVATE);
        //leer el archivo //entre parentesis poner el identificacdor de la informacion que queremos leer y depues de la coma se pone información por defecto
        String user = preferences.getString("usuario", "No existe la información");
        String pass = preferences.getString("pass", "No existe la información");
    }

    private void guardarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("credenciales", MODE_PRIVATE);

        String usuario = campoUsuario.getText().toString();
        String pass = campoPass.getText().toString();

        //asignar al archivo credenciales los datos ingresados
        SharedPreferences.Editor editor = preferences.edit();

        //utilizar el objeto creado para guardar
        editor.putString("usuario", usuario);
        editor.putString("pass", pass);

        //mostrar los datos ingresados
        txtUsuario.setText(usuario);
        txtPass.setText(pass);

        // completar el archivo y guardar los datos
        editor.commit();
    }

}