package studentmgmt.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class DBConnection
{
    /**
     * java:comp/env - Standard prefix.
     * jdbc/demo - is the JNDI Lookup name in the <Resource> </Resource> of context.xml
     * Refer to Readme.md
     * */
    public static Connection getConnection() throws SQLException
    {
        Connection connection = null;
        try{

            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/student");
            connection = ds.getConnection();
            System.out.println(connection);
        }catch(Exception e){
            throw new SQLException(e);
        }

        return connection;
    }
}