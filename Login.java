import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Login extends JFrame implements ActionListener {
    JButton reset,submit,close;
    JTextField username;
    JPasswordField password;


    public Login(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lb1username =new JLabel("Username");
        lb1username.setBounds(20,20,100,20);
        add(lb1username);

        username = new JTextField();
        username.setBounds(130,20,200,20);
        add(username);

        JLabel lb1password =new JLabel("Password");
        lb1password.setBounds(20,50,100,20);
        add(lb1password);

        password = new JPasswordField();
        password.setBounds(130,50,200,20);
        add(password);

        reset = new JButton("Reset");
        reset.setBounds(40,120,120,20);
        reset.addActionListener(this);
        add(reset);

        submit = new JButton("submit");
        submit.setBounds(190,120,120,20);
        submit.addActionListener(this);
        add(submit);

        close = new JButton("Close");
        close.setBounds(120,160,120,20);
        close.addActionListener(this);
        add(close);

        setSize(400,250);
        setLocation(450,250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
    if(ae.getSource()==submit){
          String Username = username.getText();
          String Password = password.getText();
          try{
              Conn con= new Conn();
              String query= "Select * from login where username = '" +username.getText()+"' and password= '"+password.getText()+"'";
              ResultSet rs = con.statement.executeQuery(query);


              if(rs.next()){
                  new Home();
                  setVisible(false);
              }else {
                  JOptionPane.showMessageDialog(null,"Invalid username and Password");
                  setVisible(false);
              }
          }catch (Exception e){
              e.printStackTrace();
          }
    } else if (ae.getSource()==close) {
        setVisible(false);
    } else if (ae.getSource()==reset) {
        username.setText("");
        password.setText("");
    }
    }

    public static void main(String[] args) {

        new Login();
    }
}
