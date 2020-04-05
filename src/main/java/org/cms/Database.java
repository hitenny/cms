package org.cms;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static OracleConnection connection;
    private static Statement statement;

    public static synchronized OracleConnection getDbConnection() throws Exception {
        try {
            if (connection != null) {
                return connection;
            }
            OracleDataSource ods = new OracleDataSource();

            String TNS_ADMIN=System.getenv("TNS_ADMIN");
            ods.setURL("jdbc:oracle:thin:@church_high?TNS_ADMIN=" + TNS_ADMIN);
            //ods.setURL("adb.ap-mumbai-1.oraclecloud.com:1522/lv9gojiuesjwmyp_church_high.atp.oraclecloud.com");
            ods.setUser("ADMIN");
            ods.setPassword("10Eisunexpected");

            connection = (OracleConnection) ods.getConnection();
            connection.setAutoCommit(true);
            return connection;
        } catch (SQLException e) {
            throw new Exception("Error occurred while connecting to DB" + e.getMessage());
        }
    }

    public static synchronized Statement getStatement() {
        if(statement == null) {
            try {
                statement = getDbConnection().createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statement;
    }

    public static boolean execute(String query) {
        try {
            getStatement().execute(query);
            return true;
        } catch (SQLException e) {
            System.out.println("Error occurred while executing query:" + query);
        }
        catch (Exception e) {
            System.out.println("Error occurred while connecting to DB");
        }
        return false;
    }

    public static ResultSet executeQuery(String query) {
        try {
            ResultSet resultSet = getStatement().executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Error occurred while executing query:" + query);
            System.out.println(e);

        }
        catch (Exception e) {
            System.out.println("Error occurred while connecting to DB");
        }
        return null;
    }
}
