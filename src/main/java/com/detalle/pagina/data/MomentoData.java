package com.detalle.pagina.data;

import com.detalle.pagina.domain.Momento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MomentoData {
    
    // Método para insertar (Usa INSERT directo ahora)
    public boolean insertarMomento(Momento m) throws SQLException {
        // SQL directo en lugar de {call...}
        String sql = "INSERT INTO momento (descripcion, prioridad, idCategoria) VALUES (?, ?, ?)"; 
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, m.getDescripcion());
            ps.setInt(2, m.getPrioridad());
            ps.setInt(3, m.getIdCategoria());
            
            return ps.executeUpdate() > 0;
        }
    }
    
    // Método para obtener todos los registros (Usa SELECT directo ahora)
    public List<Momento> listarMomentos() throws SQLException {
        List<Momento> lista = new ArrayList<>();
        // SELECT directo en lugar de {call...}
        String sql = "SELECT id, descripcion, prioridad, idCategoria FROM momento"; 
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Momento m = new Momento();
                m.setId(rs.getInt("id"));
                m.setDescripcion(rs.getString("descripcion"));
                m.setPrioridad(rs.getInt("prioridad"));
                m.setIdCategoria(rs.getInt("idCategoria"));
                lista.add(m);
            }
        }
        return lista;
    }

    // Método para obtener un registro por su ID (Usa SELECT con WHERE ahora)
    public Momento obtenerMomentoPorId(int idBusqueda) throws SQLException {
        Momento m = null;
        // SELECT con filtro en lugar de {call...}
        String sql = "SELECT id, descripcion, prioridad, idCategoria FROM momento WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idBusqueda);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    m = new Momento();
                    m.setId(rs.getInt("id"));
                    m.setDescripcion(rs.getString("descripcion"));
                    m.setPrioridad(rs.getInt("prioridad"));
                    m.setIdCategoria(rs.getInt("idCategoria"));
                }
            }
        }
        return m;
    }
}