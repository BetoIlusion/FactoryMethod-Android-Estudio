package com.app.loginusuario.P_FactoryMethod;

public class mostrarAdmin extends Creator{
    @Override
    public Rol mostrar() {
        return new RolAdmin();
    }
}
