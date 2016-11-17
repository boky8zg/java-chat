/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author bojan
 */
public class ClientThread extends Thread {

    private Server server;
    private Socket clientSocket;

    public ClientThread(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
            
            // Prva poruka je nick od klijenta
            String nick = dis.readUTF();
            server.setNick(clientSocket, nick);
            
            while (true) {
                String message = dis.readUTF();
                
                System.out.println("Došla poruka: " + message);
                
                server.sendEverybody(clientSocket, message);
            }
        } catch (IOException ex) {
            System.out.println("Greška u streamu sa klijentom\nException: " + ex);
        }
    }
    
}
