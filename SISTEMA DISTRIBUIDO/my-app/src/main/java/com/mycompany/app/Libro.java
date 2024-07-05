package com.mycompany.app;
public class Libro {
    String nombre;
    double numero;

    public Libro(String nombre, double numero){
        this.nombre = nombre;
        this.numero = numero;
    }

    public String getNombre(){
        return nombre;
    }
    public double getNumero(){
        return numero;
    }
}