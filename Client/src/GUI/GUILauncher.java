/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Jon Gs
 */
public class GUILauncher extends Thread {
    private final GUI gui = new GUI();

    public GUI getGui() {
        return gui;
    }
    
    public void start(){
        gui.main();
    }
}
