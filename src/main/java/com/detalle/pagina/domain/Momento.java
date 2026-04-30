package com.detalle.pagina.domain;

public class Momento {
    private int id;
    private String descripcion;
    private int prioridad; // Del 1 al 5
    private int idCategoria;

    // Constructores
    public Momento() {}

    public Momento(int id, String descripcion, int prioridad, int idCategoria) {
        this.id = id;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.idCategoria = idCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    
    // Lógica requerida por la UNA: Atributo calculado 
    // En este caso, calculamos el "Nivel de Ternura" basado en la prioridad
    public double getNivelTernura() {
        return this.prioridad * 2.5; 
    }
}