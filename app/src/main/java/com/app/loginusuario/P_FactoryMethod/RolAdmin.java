package com.app.loginusuario.P_FactoryMethod;

import android.view.View;

import com.app.loginusuario.DashBoard;
import com.app.loginusuario.vLogin;

public class RolAdmin implements Rol{
    @Override
    public void showActivity(DashBoard dashBoard) {
        dashBoard.btnPerfil.setVisibility(View.VISIBLE);
        dashBoard.btnContactos.setVisibility(View.INVISIBLE);
        dashBoard.btnProductos.setVisibility(View.INVISIBLE);
        dashBoard.btnReportes.setVisibility(View.INVISIBLE);
        dashBoard.tvPublicidad.setVisibility(View.INVISIBLE);
        dashBoard.tvLorem.setVisibility(View.INVISIBLE);
    }
}
