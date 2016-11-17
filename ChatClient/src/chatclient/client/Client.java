/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient.client;

import chatclient.gui.ChatWindow;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author bojan
 */
public class Client {

    private String host;
    private int port;
    private String nick;
    private ChatWindow chatWindow;
    private Socket server;

    public Client(String host, int port, String nick) throws IOException {
        this.host = host;
        this.port = port;
        this.nick = nick;
        this.chatWindow = new ChatWindow(this);
        this.chatWindow.setVisible(true);
        
        connect();
    }

    private void connect() throws IOException {
        server = new Socket(host, port);
        
        sendMessage(nick);
        
        ReceiverThread receiverThread = new ReceiverThread(server, chatWindow);
        receiverThread.start();
    }
    
    public void sendMessage(String message) {
        try {
            DataOutputStream dos = new DataOutputStream(server.getOutputStream());
            
            dos.writeUTF(message);
        } catch (IOException ex) {
            System.out.println("Pogreška pri slanju poruke na server!");
        }
    }
    
}
