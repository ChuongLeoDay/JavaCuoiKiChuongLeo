/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 *
 * @author PC
 */
public class ChatPanel extends javax.swing.JPanel {
    
    Socket socket = null;
    BufferedReader bf = null;
    DataOutputStream os = null;
    ServiceThread t = null;
    String sender;
    String receiver;
    
    public ChatPanel(Socket s, String sender, String receiver){
        initComponents();
        socket = s;
        this.sender = sender;
        this.receiver = receiver;
        try{
            bf = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));
            os = new DataOutputStream(socket.getOutputStream());
            t = new ServiceThread(s,tareaConversation,sender,receiver);
            t.start();
        } catch(Exception e){}
    }

    public JTextArea getTareaConversation() {
        return tareaConversation;
    }  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tareaMessage = new javax.swing.JTextArea();
        btnSend = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tareaConversation = new javax.swing.JTextArea();

        jLabel1.setText("jLabel1");

        setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Message"), "Message"));

        tareaMessage.setColumns(20);
        tareaMessage.setRows(5);
        jScrollPane3.setViewportView(tareaMessage);

        btnSend.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSend.setText("SEND");
        btnSend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSendMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        add(jPanel3, java.awt.BorderLayout.PAGE_END);

        tareaConversation.setColumns(20);
        tareaConversation.setRows(5);
        jScrollPane2.setViewportView(tareaConversation);

        add(jScrollPane2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSendMouseClicked
        if(tareaMessage.getText().trim().length()==0) return;
        try {
            os.writeBytes(tareaMessage.getText());
            os.write(13); os.write(10);
            os.flush();
            tareaConversation.append("\n"+sender+": "+tareaMessage.getText());
            tareaMessage.setText("");
        } catch (IOException ex) {
            
        }
        
    }//GEN-LAST:event_btnSendMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea tareaConversation;
    private javax.swing.JTextArea tareaMessage;
    // End of variables declaration//GEN-END:variables
}