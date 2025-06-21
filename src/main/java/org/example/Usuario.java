package org.example;

public class Usuario {
    private String name;
    private boolean activo;

    public Usuario(String name){
        this.name=name;
    }

    public boolean isActivo(){
        return activo;
    }

    public String getName(){
        return name;
    }

    public void iniciarSession(){
        activo=true;
    }

    public void cerrarSession(){
        activo=false;
    }
}
