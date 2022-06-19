
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author PC
 */
public class ServiceThread extends Thread{
    Socket socket;
    JTextArea text;
    BufferedReader bf;
    String sender;
    String receiver;

    public ServiceThread(Socket s, JTextArea txt, String sender, String receiver) {
        super();
        this.socket = s;
        this.text = txt;
        this.sender = sender;
        this.receiver = receiver;
        try {
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Can't connect");
            System.exit(0);
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                if(socket!=null) {
                    String msg = "";              
                    if((msg=bf.readLine())!=null && msg.length()>0){
                        text.append("\n"+ receiver +": "+ msg);
                    }
                }
                sleep(500);
            } catch (Exception ex) {}
        }
    }
   
    
}