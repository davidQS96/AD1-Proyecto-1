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

public class Tempo extends TimerTask {
    int seconds = 0;
    GameController gameController;
    
    public Tempo(GameController gameController){
        this.gameController = gameController;
    }

    Tempo() {
    }

    @Override
    public void run() {
        if(seconds == 0){
            System.out.println("Voy a pedir una update de grid");
            /*try {
                gameController.requestUPDATE();
            } catch (IOException ex) {
                ex.printStackTrace();
            }*/
        }
        seconds ++;
        seconds = seconds % 2;
        System.out.println(seconds);
    }
    
}
