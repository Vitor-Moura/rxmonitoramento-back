/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.ControllerHistoricoComponente;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;



/**
 *
 * @author raylane
 */
public class ModelSlackIntegration {
    private static  HttpClient client = HttpClient.newHttpClient();
    private static final  String url = "https://hooks.slack.com/services/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";

    public static  void notify(String message) {
        try{
        JSONObject json = new JSONObject();
        json.put("text", message);
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                .build();
        
        HttpResponse <String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch(Exception ex){
            Logger.getLogger(ControllerHistoricoComponente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
