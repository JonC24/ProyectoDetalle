/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.detalle.pagina.data;
import com.detalle.pagina.domain.Categoria;

import com.detalle.pagina.domain.Momento;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cjon4
 */
public class CategoriaData {
    // Método para obtener todos los registros (GET ALL)
    public List<Categoria> listarCategorias() throws SQLException {
        List<Categoria> lista = new ArrayList<>();
        String sql = "{call listarCategorias()}"; // Llamada al procedimiento
        
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            
            while (rs.next()) {
                Categoria s = new Categoria();
                s.setIdCategoria(rs.getInt("idCategoria"));
                s.setNombre(rs.getString("nombre"));
                lista.add(s);
            }
        }
        return lista;
    }

    // Método para obtener un registro por su ID (GET BY ID)
    public Categoria obtenerCategoriaPorId(int idBusqueda) throws SQLException {
        Categoria c = null;
        String sql = "{call obtenerCategoriaPorId(?)}";
        
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            
            cs.setInt(1, idBusqueda);
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    c = new Categoria();
                    c.setIdCategoria(rs.getInt("idCategoria"));
                    c.setNombre(rs.getString("nombre "));
                }
            }
        }
        return c;
    }
    
}
