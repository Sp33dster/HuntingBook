package pl.lodz.p.it.spjava.e11.huntingBook.config;

import java.sql.Connection;
import javax.annotation.sql.DataSourceDefinition;
import javax.annotation.sql.DataSourceDefinitions;
@DataSourceDefinitions ({
@DataSourceDefinition( // Ta pula po³¹czeñ jest na potrzeby operacji realizowanych przez modu³ aplikacji
        name = "java:app/jdbc/HuntingJDBCJavaDB",
        className = "org.apache.derby.jdbc.ClientDataSource",
        user = "hunter",
        password = "hunter",
        serverName = "localhost",
        portNumber = 1527,
        databaseName = "HuntingBookDB",
        transactional = true,
        isolationLevel = Connection.TRANSACTION_READ_COMMITTED)
,
@DataSourceDefinition( // Ta pula po³¹czeñ jest na potrzeby operacji realizowanych przez modu³ aplikacji
        name = "java:app/jdbc/HuntingJDBCJavaDB_Serializable",
        className = "org.apache.derby.jdbc.ClientDataSource",
        user = "hunter",
        password = "hunter",
        serverName = "localhost",
        portNumber = 1527,
        databaseName = "HuntingBookDB",
        transactional = true,
        isolationLevel = Connection.TRANSACTION_SERIALIZABLE)
})
public class JDBCConfig {

}