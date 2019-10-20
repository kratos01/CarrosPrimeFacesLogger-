/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.formularioprimefaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author David Arias
 */
@Named(value = "carros")
@ViewScoped
public class Carros implements Serializable{

    /**
     * Creates a new instance of Carros
     */
    @NotNull
    private String nombre;
    @Size(min=1,max=5)
    private String marca;
    @Min(1800)
    @Max(2050)
    private int modelo;
    private List<Carros> carro;
    private List<String> listamarca;
    
    public Carros() {
       carro = new ArrayList<>();
    }
/**
 * costructor de la calse que inicializa las variables
 * @param nombre
 * @param marca
 * @param modelo 
 */
    public Carros(String nombre, String marca, int modelo) {
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        
    }
    @PostConstruct
    public void lista(){
        listamarca = new ArrayList<>();
        
        listamarca.add("Mazda");
        listamarca.add("Ford");
        listamarca.add("Renault");
        listamarca.add("Cherolet");
        listamarca.add("Nissan");
        
        //listamarca.add("Ford");
                
    }

/**
 * getters y setters de las variables
 * @return 
 */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public List<Carros> getCarro() {
        return carro;
    }

    public void setCarro(List<Carros> carro) {
        this.carro = carro;
    }

    public List<String> getListamarca() {
        return listamarca;
    }

    public void setListamarca(List<String> listamarca) {
        this.listamarca = listamarca;
    }

   /**
    * funcion para almacenar objetos en la lista 
    */
    public void llenarLista(){
      Carros car = new Carros(nombre, marca, modelo);
      carro.add(car);
    }
}
