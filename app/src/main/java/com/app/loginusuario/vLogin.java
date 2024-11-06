package com.app.loginusuario;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;
import android.widget.Toast;

public class vLogin extends AppCompatActivity {
    private EditText etEmail, etContrasena;
    private TextView tvCrearCuenta;
    private Button btnIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHelper dbHelper = new DbHelper(vLogin.this);
        // Inicializar los EditText
        etEmail = findViewById(R.id.etEmail);
        etContrasena = findViewById(R.id.etPassword);
        tvCrearCuenta = findViewById(R.id.tvCreateAccount);
        btnIniciarSesion = findViewById(R.id.btnLogin);
        tvCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vLogin.this, UserCreate.class);
                startActivity(intent);
            }
        });
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String pass = etContrasena.getText().toString().trim();
                int id_rol = existeUsuario(email,pass);
                if(id_rol <= 0){
                    Toast.makeText(vLogin.this, "ROL ERROR", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(vLogin.this,DashBoard.class);
                    intent.putExtra("ID_rol",id_rol);
                    startActivity(intent);
                }
            }
        });

    }

    private int existeUsuario(String email, String pass) {
        DbHelper dbHelper = new DbHelper(vLogin.this);
        Cursor cursorUser = dbHelper.getAllUsuarios();
        if(cursorUser.moveToFirst()){
            do {
                if(email.equals(cursorUser.getString(1))
                && pass.equals(cursorUser.getString(2))){
                    return cursorUser.getInt(3);
                }
            }while (cursorUser.moveToNext());
        }
        return -1;
    }
}