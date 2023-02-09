/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.github.britooo.looca.api.group.processos.ProcessosGroup;
import connection.Connection;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.ModelComputadores;
import model.ModelUsuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author raylane
 */
public class ControllerUsuarioMaquina {
    Connection connection = new Connection();
    JdbcTemplate template = new JdbcTemplate(connection.getBasicDataSource());
    
    ProcessosGroup processos = new ProcessosGroup();
    ModelComputadores serviceComputadores = new ModelComputadores();


    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String selectIdMaquina = "select maquina.id from maquina where hostname=?";

    public void insertUsuarioMaquinaLogin(List<ModelUsuario> getUser) throws UnknownHostException {
        List<ModelComputadores> getIdComponentes = template.query(selectIdMaquina,
                new BeanPropertyRowMapper(ModelComputadores.class),
                serviceComputadores.getHostName());

        Date dateLogin = new Date();
        template.update("INSERT INTO usuarioMaquina (fkMaquina,fkUsuario,dataHoraLogin, estado) values (?,?,?,'ligado')",
                getIdComponentes.get(0).getId(),
                getUser.get(0).getId(),
                dateFormat.format(dateLogin));


    }

    public void insertUsuarioMaquinaLogout() throws UnknownHostException {
        List<ModelComputadores> getIdComponentes = template.query(selectIdMaquina,
                new BeanPropertyRowMapper(ModelComputadores.class),
                serviceComputadores.getHostName());

        Date dateLogout = new Date();
        template.update("UPDATE usuarioMaquina set estado='desligado', dataHoraLogout=? where fkMaquina=? and estado='ligado'",
                dateFormat.format(dateLogout),
                getIdComponentes.get(0).getId());
        
        System.out.println("Bye bye");


    }
    
    
}
