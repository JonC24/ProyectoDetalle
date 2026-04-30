package com.detalle.pagina.data;

import com.detalle.pagina.domain.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cjon4
 */
public class CategoriaData {

    // Método para obtener todos los registros (Usa SELECT directo)
    public List<Categoria> listarCategorias() throws SQLException {
        List<Categoria> lista = new ArrayList<>();
        // SQL directo en lugar de {call...}
        String sql = "SELECT idCategoria, nombre FROM categoria"; 
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Categoria s = new Categoria();
                s.setIdCategoria(rs.getInt("idCategoria"));
                s.setNombre(rs.getString("nombre"));
                lista.add(s);
            }
        }
        return lista;
    }

    // Método para obtener un registro por su ID (Usa SELECT con WHERE)
    public Categoria obtenerCategoriaPorId(int idBusqueda) throws SQLException {
        Categoria c = null;
        // SELECT filtrado en lugar de {call...}
        String sql = "SELECT idCategoria, nombre FROM categoria WHERE idCategoria = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idBusqueda);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new Categoria();
                    c.setIdCategoria(rs.getInt("idCategoria"));
                    c.setNombre(rs.getString("nombre")); // Corregido espacio extra
                }
            }
        }
        return c;
    }
}