package com.imprenta.datos;

import static com.imprenta.datos.Conexion.close;
import static com.imprenta.datos.Conexion.getConnection;
import com.imprenta.dominio.ClienteDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOJDBCimp implements IClienteDAO {

    private static final String SQL_SELECT = "SELECT * FROM cliente";

    private static final String SQL_SELECT_ID = "SELECT * FROM cliente WHERE idCliente = ?";

    private static final String SQL_INSERT = "INSERT INTO cliente "
            + "( nombre, cif, direccion, telefono) VALUES "
            + "( ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE cliente SET "
            + "nombre = ?,"
            + "cif = ?, "
            + "direccion = ?, "
            + "telefono = ? "
            + "WHERE idCliente = ?";

    private static final String SQL_UPDATE_NOMBRE = "UPDATE cliente SET "
            + "nombre = ? "
            + "WHERE idCliente = ?";
    private static final String SQL_UPDATE_CIF = "UPDATE cliente SET "
            + "cif = ? "
            + "WHERE idCliente = ?";
    private static final String SQL_UPDATE_DIRECCION = "UPDATE cliente SET "
            + "direccion = ? "
            + "WHERE idCliente = ?";
    private static final String SQL_UPDATE_TELEFONO = "UPDATE cliente SET "
            + "telefono = ? "
            + "WHERE idCliente = ?";

    private static final String SQL_DELETE = "DELETE FROM cliente "
            + "WHERE idcliente = ?";

    private static final String SQL_ORDER_ASC = "SELECT * FROM cliente "
            + "ORDER BY nombre";
    
    private static final String SQL_BUSCAR = "SELECT * FROM cliente "
            + "WHERE nombre = ? ";

    private Connection conexionTransaccional;

    public ClienteDAOJDBCimp() {
    }

    public ClienteDAOJDBCimp(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public List<ClienteDTO> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ClienteDTO> clientes = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPersona = rs.getInt("idcliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("cif");
                String email = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                clientes.add(new ClienteDTO(idPersona, nombre, apellido, email, telefono));
            }
        } finally {
            close(rs);
            close(stmt);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return clientes;
    }

    @Override
    public List<ClienteDTO> seleccionarID(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ClienteDTO> clientes = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ID);

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int idPersona = rs.getInt("idcliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("cif");
                String email = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                clientes.add(new ClienteDTO(idPersona, nombre, apellido, email, telefono));
            }
        } finally {
            if (rs != null) {
                close(rs);
            }

            if (stmt != null) {
                close(stmt);
            }
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return clientes;
    }
    @Override
    public List<ClienteDTO> buscar(String nombre) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ClienteDTO> clientes = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_BUSCAR);

            stmt.setString(1, nombre);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int idPersona = rs.getInt("idcliente");
                String apellido = rs.getString("cif");
                String email = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                clientes.add(new ClienteDTO(idPersona, nombre, apellido, email, telefono));
            }
        } finally {
            if (rs != null) {
                close(rs);
            }

            if (stmt != null) {
                close(stmt);
            }
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return clientes;
    }
    
     @Override
    public List<ClienteDTO> ordenarASC() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ClienteDTO> clientes = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_ORDER_ASC);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPersona = rs.getInt("idcliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("cif");
                String email = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                clientes.add(new ClienteDTO(idPersona, nombre, apellido, email, telefono));
            }
        } finally {
            close(rs);
            close(stmt);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return clientes;
    }

    @Override
    public int insertar(ClienteDTO cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, cliente.getNombre_completo());
            stmt.setString(2, cliente.getCif());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, cliente.getTelefono());
            registros = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            close(stmt);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return registros;
    }

    @Override
    public int actualizar(ClienteDTO cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, cliente.getNombre_completo());
            stmt.setString(2, cliente.getCif());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, cliente.getTelefono());
            stmt.setInt(5, cliente.getId_cliente());
            registros = stmt.executeUpdate();
        } finally {
            close(stmt);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return registros;
    }

    @Override
    public int borrar(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            registros = stmt.executeUpdate();
        } finally {
            close(stmt);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return registros;
    }

    @Override
    public int actualizarNombre(String nombre, int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_NOMBRE);
            stmt.setString(1, nombre);
            stmt.setInt(2, id);
            registros = stmt.executeUpdate();
        } finally {
            close(stmt);
            close(conn);
        }
        return registros;
    }

    @Override
    public int actualizarCif(String cif, int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_CIF);
            stmt.setString(1, cif);
            stmt.setInt(2, id);
            registros = stmt.executeUpdate();
        } finally {
            close(stmt);
            close(conn);
        }
        return registros;
    }

    @Override
    public int actualizarDireccion(String direccion, int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_DIRECCION);
            stmt.setString(1, direccion);
            stmt.setInt(2, id);
            registros = stmt.executeUpdate();
        } finally {
            close(stmt);
            close(conn);
        }
        return registros;
    }

    @Override
    public int actualizarTelefono(String telefono, int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_TELEFONO);
            stmt.setString(1, telefono);
            stmt.setInt(2, id);
            registros = stmt.executeUpdate();
        } finally {
            close(stmt);
            close(conn);
        }
        return registros;
    }
}
