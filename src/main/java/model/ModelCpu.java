/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.github.britooo.looca.api.group.processador.Processador;

/**
 *
 * @author raylane
 */
public class ModelCpu extends Model{
    
    private Double emUso;
    private Double frequencia;
    private Processador processador;

    public ModelCpu() {
        super("Huuummmmm, gerente ! "
                + " Parece que há um computador que não está processando muito bem ! :hushed: :computer: :fire:\n" +
"Recomendamos que verifique os aplicativos e programas abertos no computador e deixe apenas o necessário.");
        this.processador = new Processador();
        this.frequencia = Double.valueOf(processador.getFrequencia());
    }
    
    public Double emUso(){
        this.emUso = processador.getUso();
        this.notifyIfNecessary(emUso);
        this.notifyInactivity(emUso);
        return emUso;
    }
    
    public Double getfrequencia (){
        return frequencia;
    }
    
    private void notifyInactivity(Double uso){
        if(uso <= 10.0 && wasNotified == false){
            var message = " Olá, sr(a) Gerente ! Parece que há computadores inativos. :sleeping: :mag:";
            ModelSlackIntegration.notify(message);
            wasNotified = true;
         }
        
        if(uso > 10.0 && wasNotified == true){
            wasNotified = false;
        }
    }
}
