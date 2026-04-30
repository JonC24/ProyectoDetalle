package com.detalle.pagina.data;

import com.detalle.pagina.domain.Imagen;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImagenData {
    public List<Imagen> listarImagenes() throws SQLException {
        List<Imagen> lista = new ArrayList<>();
        String sql = "{call listarGaleria()}";
        
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
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