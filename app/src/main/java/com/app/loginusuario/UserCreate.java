package com.app.loginusuario;

import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UserCreate extends AppCompatActivity {
    private Spinner spRol;
    private EditText etNombre, etEmail, etContrasena;
    private Button btnCrear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        // Inicializar los EditText
        etNombre = findViewById(R.id.etNombreCreateUser);
        etEmail = findViewById(R.id.etEmailCreateUser);
        etContrasena = findViewById(R.id.etContrasenaCreateUser);
        btnCrear = findViewById(R.id.btnCrearUsuario);
        // Inicializar el Spinner
        spRol = findViewById(R.id.spRolCreateUser);
        String[] roles = {"Administrador", "Cajero"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRol.setAdapter(adapter);
        // Obtener el Spinner
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String pass = etContrasena.getText().toString().trim();
                String rol = spRol.getSelectedItem().toString().trim();
                int id_rol = 0;
                if(nombre.isEmpty() || email.isEmpty() || pass.isEmpty() || rol.isEmpty())
                    Toast.makeText(UserCreate.this, "LLENAR ESPACIOS", Toast.LENGTH_LONG).show();
                else{
                    DbHelper dbHelper = new DbHelper(UserCreate.this);
                    Cursor roles = dbHelper.getAllRoles();
                    if(roles.moveToFirst()){
                        do{
                            if(rol.equals(roles.getString(1))){
                                id_rol = roles.getInt(0);
                            }
                        }while (roles.moveToNext());
                    }
                    long id = dbHelper.addUsuario(
                            nombre,
                            email,
                            pass,
                            id_rol
                    );
                    if(id >0){
                        Toast.makeText(UserCreate.this, "USUARIO CREADO CON EXITO", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(UserCreate.this, "ERROR", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}