package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lara_
 */

public class SQLiteConnection {
private static volatile Connection connection;

    private SQLiteConnection () {

    }
    
public static Connection getInstance() {
        Connection connec = SQLiteConnection.connection;
        if (connec == null) {
            synchronized (SQLiteConnection.class) {
                connec = SQLiteConnection.connection;
                if (connec == null) {
                    try {
                        // Criando o diretório "db" caso ele não exista
                        File dbDir = new File("db");
                        if (!dbDir.exists()) {
                            dbDir.mkdir();  // Cria o diretório db
                        }

                        // Configuração do URL de conexão
                        String url = "jdbc:sqlite:db/log.db";  // Localizando o banco no diretório "db"
                        SQLiteConnection.connection = connec = DriverManager.getConnection(url);
                        System.out.println("Conexão estabelecida com sucesso!");
                    } catch (SQLException e) {
                        throw new IllegalStateException("Erro ao conectar no banco de dados: " + e.getMessage());
                    }
                }
            }
        }
        return connec;
    }
}