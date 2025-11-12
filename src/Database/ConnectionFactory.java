package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe responsável pela conexão com o banco de dados
public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/academia"; // nome do banco
    private static final String USER = "root";  // usuário do MySQL
    private static final String PASSWORD = "Root"; // senha do MySQL

    // Método para abrir a conexão
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
