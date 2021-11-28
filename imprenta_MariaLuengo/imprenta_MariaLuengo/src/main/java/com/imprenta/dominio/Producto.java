package com.imprenta.dominio;

public class Producto {

    private int id_producto;
    private String nombreProducto;
    private FormatoPapel medida;
    private boolean is2caras;
    private TipoPapel papel;
    private int gramaje;
    private boolean isColor;

    public Producto(int id_producto, String nombreProducto, FormatoPapel medida, boolean is2caras, TipoPapel papel, int gramaje, boolean isColor) {
        this.id_producto = id_producto;
        this.nombreProducto = nombreProducto;
        this.medida = medida;
        this.is2caras = is2caras;
        this.papel = papel;
        this.gramaje = gramaje;
        this.isColor = isColor;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_producto() {
        return id_producto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public FormatoPapel getMedida() {
        return medida;
    }

    public void setMedida(FormatoPapel medida) {
        this.medida = medida;
    }

    public boolean isIs2caras() {
        return is2caras;
    }

    public void setIs2caras(boolean is2caras) {
        this.is2caras = is2caras;
    }

    public TipoPapel getPapel() {
        return papel;
    }

    public void setPapel(TipoPapel papel) {
        this.papel = papel;
    }

    public int getGramaje() {
        return gramaje;
    }

    public void setGramaje(int gramaje) {
        this.gramaje = gramaje;
    }

    public boolean isIsColor() {
        return isColor;
    }

    public void setIsColor(boolean isColor) {
        this.isColor = isColor;
    }

}
