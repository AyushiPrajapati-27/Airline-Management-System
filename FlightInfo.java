import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import static net.proteanit.sql.DbUtils.resultSetToTableModel;

public class FlightInfo extends JFrame {

    public FlightInfo(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JTable table= new JTable();

        try{
            Conn conn = new Conn();
            ResultSet rs = conn.statement.executeQuery("select * from flight");
            table.setModel(resultSetToTableModel(rs));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0,0,800,500);
        add(jsp);

        setSize(800,500);
        setLocation(400,300);
        setVisible(true);
    }
    public static void main(String[] args) {

        new FlightInfo();
    }
}
