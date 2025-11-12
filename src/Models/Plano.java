package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Classe que representa os planos da academia
public class Plano {
    public int tipoPlano;
    public String valor;
    public String descricao;

    public Plano(int tipoPlano, String valor, String descricao) {
        this.tipoPlano = tipoPlano;
        this.valor = valor;
        this.descricao = descricao;
    }

    public static void create(Connection conn, Plano plano) throws SQLException {
        String sql = "INSERT INTO Planos (TipoPlano, Valor, Descricao) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, plano.tipoPlano);
            stmt.setString(2, plano.valor);
            stmt.setString(3, plano.descricao);
            stmt.executeUpdate();
        }
    }


    public static List<Plano> readAll(Connection conn) throws SQLException {
        List<Plano> planos = new ArrayList<>();
        String sql = "SELECT * FROM Planos";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                planos.add(new Plano(
                        rs.getInt("TipoPlano"),
                        rs.getString("Valor"),
                        rs.getString("Descricao")
                ));
            }
        }
        return planos;
    }
}
