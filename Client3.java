package group.chatting.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;

public class Client3 extends JFrame implements ActionListener, Runnable{
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    
    BufferedWriter writer;
    BufferedReader reader;

    Client3(){
        
            p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(128,128, 128));
        p1.setBounds(0, 0, 450, 70);
        add(p1);
        
       ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("group/chatting/application/icons/3.png"));
       Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
       ImageIcon i3 = new ImageIcon(i2);
       JLabel l1 = new JLabel(i3);
       l1.setBounds(5, 17, 30, 30);
       p1.add(l1);
       
       l1.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent ae){
               System.exit(0);
           }
       });
       
       
       JLabel l3 = new JLabel("CLIENT 3");
       l3.setFont(new Font("SAN_SERIF", Font.BOLD, 30));
       l3.setForeground(Color.WHITE);
       l3.setBounds(110, 15, 150, 30);
       p1.add(l3);    
       
       
       a1 = new JTextArea();
       a1.setBounds(5, 75, 440, 570);
       a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       a1.setEditable(false);
       a1.setLineWrap(true);
       a1.setWrapStyleWord(true);
       add(a1);
       
       
       t1 = new JTextField();
       t1.setBounds(5, 655, 310, 40);
       t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       add(t1);
       
       b1 = new JButton("Send");
       b1.setBounds(320, 655, 123, 40);
       b1.setBackground(new Color(255, 255, 255));
       b1.setForeground(Color.BLACK);
       b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       b1.addActionListener(this);
       add(b1);
        
       getContentPane().setBackground(Color.WHITE);
       setLayout(null);
       setSize(450, 700);
       setLocation(890, 0); 
       setUndecorated(true);
       setVisible(true);
       
       try{
           
           Socket socketClient = new Socket("localhost", 2003);
           writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
           reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
       }catch(Exception e){}
       
        
    }
    
    public void actionPerformed(ActionEvent ae){
        String str = "Client 3\n"+t1.getText();
        try{
            writer.write(str);
            writer.write("\r\n");
            writer.flush();
        }catch(Exception e){}
        t1.setText("");
    }
    
    public void run(){
        try{
            String msg = "";
            while((msg = reader.readLine()) != null){
                a1.append(msg + "\n");
            }
        }catch(Exception e){}
    }
    
    public static void main(String[] args){
        Client3 one = new Client3();
        Thread t1 = new Thread(one);
        t1.start();
    }
    
}
