
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;


public class Cancel extends JFrame implements ActionListener {

    JTextField tfpnr;
    JLabel tfname, Cancellation, lblfcode, lbldateoftravel;
    JButton fetchButton, flight;

    public Cancel() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        Random random = new Random();

        JLabel heading = new JLabel(("CANCELLATION"));
        heading.setBounds(180, 20, 250, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470, 120, 250, 250);
        add(image);

        JLabel lblaadhar = new JLabel(("PNR Number"));
        lblaadhar.setBounds(60, 80, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);

        tfpnr = new JTextField();
        tfpnr.setBounds(220, 80, 150, 25);
        add(tfpnr);

        fetchButton = new JButton("Show Details");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblname = new JLabel(("Name"));
        lblname.setBounds(60, 120, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);

        tfname = new JLabel();
        tfname.setBounds(220, 120, 150, 25);
        add(tfname);


        JLabel lblCancellation = new JLabel(("Cancellation"));
        lblCancellation.setBounds(60, 160, 150, 25);
        lblCancellation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblCancellation);

        Cancellation = new JLabel("" + random.nextInt(1000000));
        Cancellation.setBounds(220, 160, 150, 25);
        add(Cancellation);


        JLabel lbladdress = new JLabel(("Flight Code"));
        lbladdress.setBounds(60, 200, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);

        lblfcode = new JLabel();
        lblfcode.setBounds(220, 200, 150, 25);
        add(lblfcode);

        JLabel lblgender = new JLabel(("Date"));
        lblgender.setBounds(60, 240, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);

        lbldateoftravel = new JLabel(("Gender"));
        lbldateoftravel.setBounds(220, 240, 150, 25);
        add(lbldateoftravel);


        flight = new JButton("Cancel");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(250, 300, 120, 25);
        flight.addActionListener(this);
        add(flight);


        setSize(800, 450);
        setLocation(350, 150);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String pnr = tfpnr.getText();
            try {
                Conn conn = new Conn();

                String query = "select * from reservation where PNR = '" + pnr + "'";
                ResultSet rs = conn.statement.executeQuery(query);

                if (rs.next()) {
                    tfname.setText(rs.getString("name"));
                    lblfcode.setText(rs.getString("flightcode"));
                    lbldateoftravel.setText(rs.getString("ddate"));

                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct PNR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == flight) {
            String name = tfname.getText();
            String pnr = tfpnr.getText();
            String cancelno = Cancellation.getText();
            String fcode = lblfcode.getText();
            String date = lbldateoftravel.getText();
            try {
                Conn conn = new Conn();

                String query = "insert into cancel values ('"+pnr+"' , '" + name + "','"+cancelno+"','"+fcode+"','"+date+"')";

                conn.statement.executeUpdate(query);
                conn.statement.executeUpdate("delete from reservation where PNR ='"+pnr+"'");

                JOptionPane.showMessageDialog(null, "Ticket Cancelled");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
      }



    public static void main(String[] args) {

        new Cancel();
    }
}


