package lk.ijse.posbackend.db;


import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {

    private static DbConnection dbConnection;
    Connection connection;
    private DbConnection() throws ClassNotFoundException, SQLException {

        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:/comp/env/jdbc/eshanPosSystem");
            pool.getConnection();

            this.connection = pool.getConnection();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static DbConnection getDbConnection() throws SQLException, ClassNotFoundException {
        return dbConnection == null ? dbConnection= new DbConnection() : dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
