package com.app.loginusuario.P_FactoryMethod;

import com.app.loginusuario.DashBoard;

public abstract class Creator {
    public void someOperation(DashBoard dashBoard){
        Rol rol = mostrar();
        mostrar().showActivity(dashBoard);
    }
    public abstract Rol mostrar();
}
