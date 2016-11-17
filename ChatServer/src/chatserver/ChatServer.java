/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import chatserver.server.Server;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bojan
 */
public class ChatServer {

    public static final int PORT = 5000;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Server server = new Server(5000);
        
        try {
            server.start();
        } catch (IOException ex) {
            System.out.println("Error\nIOException: " + ex);
        }
    }
    
}
