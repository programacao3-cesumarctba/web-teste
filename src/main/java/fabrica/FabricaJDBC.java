package fabrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Autor: Maycon de Moraes
 * Descrição: Classe para retornar a instacia da  conexão JDBC com o
 * banco local SQLite
 */

public class FabricaJDBC {

    public static Connection criaConn () {
        Connection conn = null;
        try {
            String path = System.getProperty("user.dir"); // diretorio do projeto
            String url = "jdbc:sqlite:" + path + "/database/schema.db"; // url de conexão
            conn = DriverManager.getConnection(url);
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}