import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;

import net.proteanit.sql.DbUtils;


@SuppressWarnings("serial")
public class Highscore extends JFrame {
	JFrame f=new JFrame();
	private JTable table;
	      
	public Highscore() 
	{
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setTitle("High Scores");
		f.setBounds(455,250, 450, 300);
		f.setVisible(true);
        f.getContentPane().setBackground(UIManager.getColor("ProgressBar.shadow"));
        f.getContentPane().setLayout(null);

        ResultSet rs = null;
table = new JTable();
table.setBounds(10, 11, 414, 239);
f.getContentPane().add(table);
table.setBackground(new Color(240, 255, 240));
table.setFont(new Font("Georgia", Font.BOLD, 13));
table.setRowSelectionAllowed(false);
table.setBorder(new CompoundBorder());

try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","a" );
        Statement stmt=con.createStatement();  
       
        String str=("select * from highscore");
		   rs=stmt.executeQuery(str);
table.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch(ClassNotFoundException  |SQLException e)
    {
		
		   JOptionPane.showMessageDialog(null,"No projects found");

    }   
	}
	public static void main(String[] args)
	{
	Highscore h=new Highscore();	
	h.setVisible(true);
	}
	
}
