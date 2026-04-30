package com.detalle.pagina.data;

import com.detalle.pagina.domain.Imagen;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImagenData {
    public List<Imagen> listarImagenes() throws SQLException {
        List<Imagen> lista = new ArrayList<>();
        // Cambiamos el {call listarGaleria()} por el SELECT directo
        String sql = "SELECT id, url, pieDeFoto FROM galeria";
        
        try (Connection conn = DBConnection.getConnection();
             // Usamos PreparedStatement en lugar de CallableStatement
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(new Imagen(
                    rs.getInt("id"),
                    rs.getString("url"),
                    rs.getString("pieDeFoto")
                ));
            }
        }
        return lista;
    }
}