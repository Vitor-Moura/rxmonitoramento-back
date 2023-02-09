
package connection;

import org.apache.commons.dbcp2.BasicDataSource;
/**
 *
 * @author  grupo5- 2 ADSA
 */

public class Connection {
    private BasicDataSource dataSource;
        
    //Conexão
    public Connection(){
        dataSource= new  BasicDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");     
        dataSource.setUrl("jdbc:sqlserver://projeto-rxmonitoramento.database.windows.net:1433;database=bd-grupo5-rxmonitoramento;user=admin-rxmonitoramento@projeto-rxmonitoramento;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        dataSource.setUsername("admin-rxmonitoramento");
        dataSource.setPassword("2ads@grupo5");
        
    }
    
    public BasicDataSource getBasicDataSource(){
        return dataSource; 
    }
    
}
