package com.imprenta.dominio;

public class ClienteDTO {

    private int id_cliente;
    private String nombreCompleto;
    private String cif;
    private String direccion;
    private String telefono;
    
    public ClienteDTO(String nombreCompleto, String cif, String direccion, String telefono) {
        this.id_cliente = id_cliente;
        this.nombreCompleto = nombreCompleto;
        this.cif = cif;
        this.direccion = direccion;
        this.telefono = telefono;
    }

   public ClienteDTO(int id_cliente, String nombreCompleto, String cif, String direccion, String telefono) {
        this.id_cliente = id_cliente;
        //this.id_cliente = ++this.contadorClientes;
        this.nombreCompleto = nombreCompleto;
        this.cif = cif;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    
    public int getId_cliente() {
        return id_cliente;
    }
    public String getNombre_completo() {
        return nombreCompleto;
    }

    public void setNombre_completo(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente: " + id_cliente +  " | " + nombreCompleto +  " | CIF: " + cif +  " | " + direccion +  " | T." + telefono ;
    }

}
