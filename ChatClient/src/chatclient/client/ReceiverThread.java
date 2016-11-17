/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient.client;

import chatclient.gui.ChatWindow;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author bojan
 */
public class ReceiverThread extends Thread {
    
    private Socket server;
    private ChatWindow chatWindow;
    
    public ReceiverThread(Socket server, ChatWindow chatWindow) {
        this.server = server;
        this.chatWindow = chatWindow;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(server.getInputStream());
            
            while (true) {                
                String nick = dis.readUTF();
                String message = dis.readUTF();
                
                chatWindow.receivedMessage(nick, message);
            }
        } catch (IOException ex) {
            System.out.println("Ne mogu primiti poruku od servera!");
        }
    }
    
}
