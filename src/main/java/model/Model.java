/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author raylane
 */
public class  Model {
    protected boolean wasNotified = false;
    protected String message;

    public Model(String message) {
        this.message = message;
    }
    
    
     protected void notifyIfNecessary(Double uso){
        if(uso >= 80.0 && wasNotified == false){
            ModelSlackIntegration.notify(message);
            wasNotified = true;
         }
        
        if(uso < 80.0 && wasNotified == true){
            wasNotified = false;
        }
    }
}
