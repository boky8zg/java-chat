/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author bojan
 */
public class Server {
    
    private int port;
    private ServerSocket serverSocket;
    private List<Socket> clients;
    private HashMap<Socket, String> nicknames;
    
    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
        this.nicknames = new HashMap<>();
    }

    public void start() throws IOException {
        // Startamo server na proslijeđenom port-u
        serverSocket = new ServerSocket(port);
        
        System.out.println("Server je startan i sluša na portu " + port);
        
        // Beskonačna serverska petlja u kojoj prihvaćamo konekcije od klijenata
        while (true) {
            // Čekamo da se klijent spoji na "nas", tj. ovaj server
            Socket clientSocket = serverSocket.accept();
            
            clients.add(clientSocket);
            
            System.out.println("Spojio se klijent! IP:" + clientSocket.getRemoteSocketAddress().toString());
            
            // Kada se klijent spojio, kreiramo mu novi thread
            ClientThread clientThread = new ClientThread(this, clientSocket);
            clientThread.start();
        }
    }

    public void sendEverybody(Socket sender, String message) {
        String nick = nicknames.get(sender);
        
        for (Socket client : clients) {
            try {
                send(client, nick, message);
            } catch (IOException ex) {
                System.out.println("Greška u slanju poruke!");
            }
        }
    }
    
    public void send(Socket client, String nick, String message) throws IOException {
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        
        dos.writeUTF(nick);
        dos.writeUTF(message);
    }

    void setNick(Socket client, String nick) {
        nicknames.put(client, nick);
    }
    
}
