package quiz.application;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Score extends JFrame implements ActionListener {
        String name;
        int score;
    
        Score(String name, int score) {
        
            this.name = name;

            setBounds(400, 150, 750, 550);
            getContentPane().setBackground(Color.WHITE);
            setLayout(null);

            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/score.png"));
            Image i2 = i1.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            image.setBounds(0, 200, 300, 250);
            add(image);

            JLabel heading = new JLabel("Thank you " + name + " for playing Simple Minds");
            heading.setBounds(45, 30, 700, 30);
            heading.setFont(new Font("Tahoma", Font.PLAIN, 26));
            add(heading);

            JLabel lblscore = new JLabel("Your score is " + score);
            lblscore.setBounds(350, 200, 300, 30);
            lblscore.setFont(new Font("Tahoma", Font.PLAIN, 26));
            add(lblscore);

            JButton submit = new JButton("Play Again");
            submit.setBounds(380, 270, 120, 30);
            submit.setBackground(new Color(30, 144, 255));
            submit.setForeground(Color.WHITE);
            submit.addActionListener(this);
            add(submit);

            insertScoreIntoDatabase();

            setVisible(true);
        }

        private void insertScoreIntoDatabase() {
            Connection con = null;
            Statement statement = null;
            ResultSet resultSet = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");

                // Establish a connection to the database
                con =DriverManager.getConnection("jdbc:mysql://localhost/quiz", "root", "");
                System.out.println("Connection established");

                // Retrieve the roll number based on the provided name
                statement = con.createStatement();
                resultSet = statement.executeQuery("SELECT roll_no FROM data WHERE name = '" + this.name + "'");
                
                // Assuming the roll number is in the first column of the result set
                if (resultSet.next()) {
                    System.out.println("successfully");
                    String roll_no = resultSet.getString(1);

                    // Update the user's score in the database
                    statement.executeUpdate("UPDATE data SET score = " + score + " WHERE roll_no = '" + roll_no + "'");
                    System.out.println("Score updated successfully");
                }
                
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
}


        
        
        
        
    
    
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        
         insertScoreIntoDatabase();
        new Login();
    }

    public static void main(String[] args) {
        new Score("user", 0);
    }
}