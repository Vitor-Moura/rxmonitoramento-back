/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.net.UnknownHostException;
import model.ModelDiscos;
import model.ModelMemoria;

/**
 *
 * @author raylane
 */
public class Teste {
    public static void main(String[] args) throws UnknownHostException {
        ModelDiscos discos = new ModelDiscos();
        
        System.out.println("Disco disponivel:  "+ discos.getDisponivel());
        System.out.println("Disco em uso:  "+ discos.getEmUso());
        
        
        
        ModelMemoria memoria = new ModelMemoria();
        System.out.println("Memoria disponivel:  "+ memoria.getMemoriaDisponivel());
        System.out.println("Memoria em uso:  "+ memoria.getMemoriaEmUso());
        
        
        
    }
    
    
}
