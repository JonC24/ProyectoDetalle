package com.detalle.pagina.data;

import com.detalle.pagina.domain.Momento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MomentoData {
    
    public boolean insertarMomento(Momento m) throws SQLException {
        String sql = "{call insertarMomento(?, ?, ?)}"; // Llamada al procedimiento 
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            
            cs.setString(1, m.getDescripcion());
            cs.setInt(2, m.getPrioridad());
            cs.setInt(3, m.getIdCategoria());
            
            return cs.executeUpdate() > 0;
        }
    }
    
    // Método para obtener todos los registros (GET ALL)
    public List<Momento> listarMomentos() throws SQLException {
        List<Momento> lista = new ArrayList<>();
        String sql = "{call listarMomentos()}"; // Llamada al procedimiento
        
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            
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

    // Método para obtener un registro por su ID (GET BY ID)
    public Momento obtenerMomentoPorId(int idBusqueda) throws SQLException {
        Momento m = null;
        String sql = "{call obtenerMomentoPorId(?)}";
        
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            
            cs.setInt(1, idBusqueda);
            
            try (ResultSet rs = cs.executeQuery()) {
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