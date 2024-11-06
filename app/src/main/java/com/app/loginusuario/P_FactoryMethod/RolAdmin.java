package com.app.loginusuario.P_FactoryMethod;

import android.view.View;

import com.app.loginusuario.DashBoard;
import com.app.loginusuario.vLogin;

public class RolAdmin implements Rol{
    @Override
    public void showActivity(DashBoard dashBoard) {
        dashBoard.btnPerfil.setVisibility(View.VISIBLE);
        dashBoard.btnContactos.setVisibility(View.VISIBLE);
        dashBoard.btnProductos.setVisibility(View.VISIBLE);
        dashBoard.btnReportes.setVisibility(View.VISIBLE);
        dashBoard.tvPublicidad.setVisibility(View.INVISIBLE);
        dashBoard.tvLorem.setVisibility(View.INVISIBLE);
    }
}
