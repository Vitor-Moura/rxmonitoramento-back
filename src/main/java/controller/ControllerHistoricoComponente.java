/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connection.Connection;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import model.ModelComputadores;
import org.springframework.jdbc.core.JdbcTemplate;
import model.ModelCpu;
import model.ModelDiscos;
import model.ModelMemoria;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author raylane
 */

public class ControllerHistoricoComponente {

    ModelCpu serviceCpu = new ModelCpu();
    ModelMemoria serviceMemoria = new ModelMemoria();
    ModelDiscos serviceDisco = new ModelDiscos();
    ModelComputadores serviceComputadores = new ModelComputadores(); 
    Connection connection = new Connection();
    JdbcTemplate template = new JdbcTemplate(connection.getBasicDataSource());

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void insertHistoricoComponentes() throws UnknownHostException {
        var selectIdComponentes = "SELECT componentes.id FROM componentes JOIN maquina ON maquina.id=componentes.fkmaquina WHERE maquina.hostname=?";
        var insertHistComponente = "INSERT INTO historicoComponente(cpuHist,memoriaHist,dataHora,fkComponentes,discoHist) values (?,?,?,?,?)";
        var existsFkComponentes = "SELECT * FROM agoraComponente WHERE fkComponentes=?";
        var insertFkComponentes = "INSERT INTO agoraComponente(fkComponentes) VALUES (?)";
        var insertHistComponenteSlack = "UPDATE agoraComponente SET cpuAgora=?,memoriaAgora=?,dataHora=?, discoAgora=? WHERE fkComponentes=? ";
        
        
          List<ModelComputadores> getIdComponentes = template.query(selectIdComponentes,
                new BeanPropertyRowMapper(ModelComputadores.class),
                serviceComputadores.getHostName());
        
          List<ModelComputadores> existsComponentes = template.query(existsFkComponentes,
                   new BeanPropertyRowMapper(ModelComputadores.class),
                   getIdComponentes.get(0).getId());
          
          if(existsComponentes.isEmpty()){
              template.update(insertFkComponentes,
                      getIdComponentes.get(0).getId());
          }
        
        
        
       var timer = new Timer();
       var delay = 5000;
       var interval = 10000;
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               Date date = new Date();               
                
                    template.update(insertHistComponente,
                            serviceCpu.emUso(),
                            serviceMemoria.getMemoriaEmUso(),
                            dateFormat.format(date),
                            getIdComponentes.get(0).getId(),
                            serviceDisco.getEmUso());
                                                        
                    template.update(insertHistComponenteSlack,
                            serviceCpu.emUso(),
                            serviceMemoria.getMemoriaEmUso(),
                            dateFormat.format(date),
                            serviceDisco.getEmUso(),
                            getIdComponentes.get(0).getId()
                            );

                    System.out.println("-".repeat(72));
                    System.out.println("RX-MONITORAMENTO : Executando Controller Historico Componentes. \n"
                            + "Coletando e inserindo dados em tempo real dos componentes da máquina");

            }
        }, delay, interval);
    }

}
