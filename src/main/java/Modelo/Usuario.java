package Modelo;

public class Usuario {
    private String name;
    private String clave;
    private boolean activo;

    public Usuario(String name, String clave){
        this.name=name;
        this.clave=clave;
    }

    public boolean isActivo(){
        return activo;
    }

    public String getClave(){
        return clave;
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