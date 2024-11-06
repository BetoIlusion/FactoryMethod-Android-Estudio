package com.app.loginusuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.loginusuario.P_FactoryMethod.Creator;
import com.app.loginusuario.P_FactoryMethod.mostrarAdmin;
import com.app.loginusuario.P_FactoryMethod.mostrarCajero;
import com.app.loginusuario.view.vContactos;
import com.app.loginusuario.view.vProducto;

public class DashBoard extends AppCompatActivity {
    public Button btnPerfil, btnContactos, btnProductos, btnReportes;
    public TextView tvPublicidad, tvLorem;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnContactos = findViewById(R.id.btnContactos);
        btnProductos = findViewById(R.id.btnProductos);
        btnReportes = findViewById(R.id.btnReportes);
        tvPublicidad = findViewById(R.id.tvPublicidad);
        tvLorem = findViewById(R.id.tvLoremIpsum);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null)
                id = Integer.parseInt(null);
            else
                id = extras.getInt("ID_rol");
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }
        // ---------------------CLIENTE ------------------------------------
        Creator creator = null;
        if(id == 1){
            creator = new mostrarAdmin();
        }else if(id == 2){
            creator = new mostrarCajero();
        }
        creator.someOperation(DashBoard.this);
        //-----------------------------------------------------------------
        btnProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoard.this, vProducto.class);
                startActivity(intent);
            }
        });
        btnContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoard.this, vContactos.class);
                startActivity(intent);
            }
        });
    }
}