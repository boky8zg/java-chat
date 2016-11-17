/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import chatclient.gui.Intro;

/**
 *
 * @author bojan
 */
public class ChatClient {

    public static final String HOST = "bdosen.ddns.net";
    public static final int PORT = 5000;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Intro intro = new Intro(HOST, PORT);
        
        intro.setVisible(true);
    }
    
}
