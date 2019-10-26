/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.formularioprimefaces;

import com.udec.formularioprimefaces.modelo.Carro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.primefaces.event.RowEditEvent;

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
    @Size(min=1,max=8)
    private String marca;
    @Min(1800)
    @Max(2050)
    private int modelo;
    private boolean seleccionar;
    
    //private Carro car;
    private List<Carros> carro;
    private List<String> listamarca;
    private List<Carros> eliminados;
    
    public Carros() {
       carro = new ArrayList<>();
       eliminados = new ArrayList<>();
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
    
//    public Carro getCar() {
//        return car;
//    }
//
//    public void setCar(Carro car) {
//        this.car = car;
//    }

    public boolean isSeleccionar() {
        return seleccionar;
    }

    public void setSeleccionar(boolean seleccionar) {
        this.seleccionar = seleccionar;
    }
    

   /**
    * funcion para almacenar objetos en la lista 
    */
    public void llenarLista(){
      Carros car = new Carros(nombre, marca, modelo);
      carro.add(car);
      Logger.getLogger(Carros.class.getName()).log(Level.INFO, "Se Ha Agregado un Carro a la Lista ");
    }
    
    /**
     * metodo para editar un carro
     * @param event 
     */
    
       public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Carro Editado", ((Carros) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Logger.getLogger("logger").log(Level.INFO, "Se Edito el Carro: " + getNombre());
    }
     /**
      * metodo para cancelar la edicion
      * @param event 
      */
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion Cancelada", ((Carros) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Logger.getLogger(Carros.class.getName()).log(Level.INFO, "Se Cancelo la Edicion");
    }
    /**
     * metodo para cambiar el antiguo valor por el nuevo
     * @param event 
     */
     public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
           Logger.getLogger("Logger").log(Level.INFO, "Se Activo La Edicion");
    }
     /**
      * metodo para eliminar un carro de la lista
      * @return 
      */
     public String eliminar(){
         for(Carros c : carro){
          if(c.isSeleccionar()){
            eliminados.add(c);
           Logger.getLogger(Carros.class.getName()).log(Level.INFO, "Se Elimino: " +"Nombre: "+ c.getNombre()+" "+"Marca: " +c.getMarca());
          }
         
        }
      if(!eliminados.isEmpty()){
              carro.removeAll(eliminados);
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado con Exito!"));
          } 
        
         
         return "carros";     
     }

}
