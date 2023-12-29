package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
 
    JButton rules, back,start;
    JTextField tfname,roll;
    String name;
    int defaultScore = 0;
    
    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpeg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 600, 500);
        add(image);
        
        JLabel heading = new JLabel("Simple Minds");
        heading.setBounds(750, 60, 300, 45);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);
        
        JLabel name = new JLabel("Enter your name");
        name.setBounds(810, 150, 300, 20);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        name.setForeground(new Color(30, 144, 254));
        add(name);
        
        tfname = new JTextField();
        tfname.setBounds(735, 200, 300, 25);
        tfname.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfname);
        
         
        JLabel rollno = new JLabel("Enter your roll number");  // Corrected label text
        rollno.setBounds(810, 250, 300, 20);  // Adjusted the position
        rollno.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        rollno.setForeground(new Color(30, 144, 254));
        add(rollno);
        
        roll = new JTextField();
        roll.setBounds(735, 270, 300, 25);  // Adjusted the position
        roll.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(roll);
        
        rules = new JButton("Rules");
        rules.setBounds(735, 300, 120, 25);
        rules.setBackground(new Color(30, 144, 254));
        rules.setForeground(Color.WHITE);
        rules.addActionListener(this);
        add(rules);
        
        back = new JButton("Back");
        back.setBounds(915, 300, 120, 25);
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        start = new JButton("start");
        start.setBounds(820, 350, 120, 25);
        start.setBackground(new Color(30, 144, 254));
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        add(start);
        
        
        setSize(1200, 500);
        setLocation(200, 150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == rules) {
            name = tfname.getText();
            setVisible(false);
            new Rules(name);
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
        else if (ae.getSource() == start) {
            ActionPerformed(ae);
            setVisible(false);
            new Quiz(name);
        } 
    }
    
     public void ActionPerformed(ActionEvent evt){
    
        String name = tfname.getText();
        String roll_no = roll.getText();
        Connection con=null;
        
        try{
             Class.forName("com.mysql.jdbc.Driver");
            
            // Establish a connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost/quiz", "root", "");
             System.out.println("connection");
           Statement statement = con.createStatement();
            
            System.out.println("Before executing query");
            statement.executeUpdate("Insert into data values ('"+name+"', '"+roll_no+"'," +defaultScore+ ")");
            
            System.out.println("Data inserted successfully");
            
        }
        catch (Exception e){
            System.out.println(e);
        }
    
    
    }
   
   
    
    public static void main(String[] args) {
        new Login();
    }
}