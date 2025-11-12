package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Classe que representa um aluno da academia
public class Aluno {
    public String cpf;
    public String nome;
    public String telefone;
    public String email;
    public int planoTipo;

    public Aluno(String cpf, String nome, String telefone, String email, int planoTipo) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.planoTipo = planoTipo;
    }

    // Inserir aluno no banco
    public static void create(Connection conn, Aluno aluno) throws SQLException {
        String sql = "INSERT INTO Alunos (CPF, Nome, DataNascimento, Telefone, Email, Planos_TipoPlano) VALUES (?, ?, NOW(), ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.cpf);
            stmt.setString(2, aluno.nome);
            stmt.setString(3, aluno.telefone);
            stmt.setString(4, aluno.email);
            stmt.setInt(5, aluno.planoTipo);
            stmt.executeUpdate();
        }
    }

    // Buscar aluno pelo CPF
    public static Aluno read(Connection conn, String cpf) throws SQLException {
        String sql = "SELECT * FROM Alunos WHERE CPF = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Aluno(
                        rs.getString("CPF"),
                        rs.getString("Nome"),
                        rs.getString("Telefone"),
                        rs.getString("Email"),
                        rs.getInt("Planos_TipoPlano")
                );
            }
        }
        return null;
    }

    // Listar todos os alunos
    public static List<Aluno> readAll(Connection conn) throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM Alunos";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                alunos.add(new Aluno(
                        rs.getString("CPF"),
                        rs.getString("Nome"),
                        rs.getString("Telefone"),
                        rs.getString("Email"),
                        rs.getInt("Planos_TipoPlano")
                ));
            }
        }
        return alunos;
    }

    // Deletar aluno
    public static void delete(Connection conn, String cpf) throws SQLException {
        String sql = "DELETE FROM Alunos WHERE CPF = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
    }
}
