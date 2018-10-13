/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

public class Tempo extends TimerTask {
    int totalSeconds = 0;
    int seconds = 0;

    public int getSeconds() {
        return seconds;
    }
    GameController gameController;
    
    public Tempo(GameController gameController, int seconds){
        this.gameController = gameController;
        this.seconds = seconds;
    }
    
    Tempo() {
    }

    @Override
    public void run() {
        Platform.runLater(new Runnable(){
            public void run(){
            if(!gameController.isIsDrawingLine()){
        System.out.println("Entre a pedir tiempo");
        if(seconds == 0){
            try {
                gameController.requestUPDATE();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                Logger.getLogger(Tempo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        totalSeconds ++;
        seconds = seconds % 2;
        gameController.updateTime(totalSeconds);
        System.out.println(seconds);
    }
            }
        });
        
    }
    
}
