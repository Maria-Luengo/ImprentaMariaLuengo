package com.imprenta.datos;

import com.imprenta.dominio.ClienteDTO;
import java.sql.SQLException;
import java.util.List;

public interface IClienteDAO {

    List<ClienteDTO> seleccionar() throws SQLException;
    List<ClienteDTO> seleccionarID(int id) throws SQLException;
    List<ClienteDTO> buscar(String nombre) throws SQLException;
    List<ClienteDTO> ordenarASC() throws SQLException;

    int insertar(ClienteDTO cliente) throws SQLException;

    int actualizar(ClienteDTO cliente) throws SQLException;

    int actualizarNombre(String nombre, int id) throws SQLException;

    int actualizarCif(String cif, int id) throws SQLException;

    int actualizarDireccion(String direccion, int id) throws SQLException;

    int actualizarTelefono(String telefono, int id) throws SQLException;

    int borrar(int id) throws SQLException;
    
    
    
}
