package dao;

import static dao.SQLiteConnection.getInstance;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Log;

/**
 *
 * @author lara_
 */

public class DBAdapter {
    private Connection connection;

    //Construtor
    public DBAdapter() {
        try {
            // Conectando ao banco de dados SQLite
            connection = getInstance();
            
            // Criando a tabela de logs, caso não exista
            Statement stmt = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS logs ("
                + "usuario TEXT, "
                + "data TEXT, "
                + "hora TEXT, "
                + "codigo_pedido INTEGER, "
                + "nome_operacao TEXT, "
                + "nome_cliente TEXT)";
            stmt.execute(createTableQuery);
        } catch (SQLException e) {
            
        }
    }


    public void inserir(Log log) {
        // Preenchendo os dados do log
        String insertQuery = "INSERT INTO logs (usuario, data, hora, codigo_pedido, nome_operacao, nome_cliente) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, log.getNomeUsuario());
            pstmt.setString(2, log.getData()); 
            pstmt.setString(3, log.getHora()); 
            pstmt.setInt(4, log.getCodigoPedido());
            pstmt.setString(5, log.getNomeOperacao());
            pstmt.setString(6, log.getNomeCliente());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}