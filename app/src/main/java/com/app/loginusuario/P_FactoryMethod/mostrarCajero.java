package com.app.loginusuario.P_FactoryMethod;

public class mostrarCajero extends Creator{
    @Override
    public Rol mostrar() {
        return new RolCajero();
    }
}
