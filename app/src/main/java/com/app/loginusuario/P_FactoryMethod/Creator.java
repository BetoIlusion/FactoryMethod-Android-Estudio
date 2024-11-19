package com.app.loginusuario.P_FactoryMethod;

import com.app.loginusuario.DashBoard;

public abstract class Creator {
    public void someOperation(DashBoard dashBoard){
        Rol rol = mostrar();
        rol.showActivity(dashBoard);
    }
    public abstract Rol mostrar();
}
