/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import com.github.britooo.looca.api.core.Looca;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author  grupo5- 2 ADSA
 */
public class TesteConnection {
    public static void main(String[] args) {
        Connection conexao = new Connection();
        // Looca looca = new Looca();
        
        JdbcTemplate template = new JdbcTemplate(conexao.getBasicDataSource());
        
        template.execute("INSERT INTO usuario VALUES (null,'Admin',"
                + "'admin@rxmonitoramento.com',"
                + "'admin123',"
                + "null,"
                + "null)");
        
        //template.update("INSERT INTO componentes VALUES (?, ?, ?, ?, ?, ?)",
        //        null,
        //        looca.getProcessador().getFrequencia(),
        //        looca.getMemoria().getTotal(),
        //       looca.getGrupoDeDiscos().getTamanhoTotal(),
        //        null,
        //        null);
            
        
        List select = template.queryForList("SELECT * FROM usuario");
        
        System.out.println(select);
        
        
    }
    
}
