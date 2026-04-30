package com.detalle.pagina.domain;

public class Imagen {
    private int id;
    private String url;
    private String pieDeFoto;

    public Imagen() {}

    public Imagen(int id, String url, String pieDeFoto) {
        this.id = id;
        this.url = url;
        this.pieDeFoto = pieDeFoto;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getPieDeFoto() { return pieDeFoto; }
    public void setPieDeFoto(String pieDeFoto) { this.pieDeFoto = pieDeFoto; }
}