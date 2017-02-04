import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Main_Game extends JFrame implements ActionListener {

	private JPanel contentPane;
	static int inc=0;
	static Main_Game frame;

	private JTextField textField;
	String entered=null;
	static int rnum=0,count=5;
	float score=0;
	static JLabel label_1 = new JLabel("");
	JButton button = new JButton("Check");
	JButton button_1 = new JButton("Hint");
	JLabel lblTriesLeft = new JLabel("Tries Left:");
	JLabel lblScore = new JLabel("Score:");


	public Main_Game() {
		setTitle("Guess A Random Number");
		setForeground(SystemColor.controlLtHighlight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(455,250, 426, 365);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setForeground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 410, 326);
		contentPane.add(panel);

		JLabel label = new JLabel("Guess a Number between ");
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		label.setBounds(10, 83, 190, 27);
		panel.add(label);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(163, 133, 89, 22);
		panel.add(textField);

		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBounds(111, 176, 89, 32);
		panel.add(button);
		button.addActionListener(this);

		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBounds(224, 176, 89, 32);
		panel.add(button_1);
		button_1.addActionListener(this);

		label_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		label_1.setBounds(194, 83, 89, 27);
		panel.add(label_1);

		lblTriesLeft.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTriesLeft.setBounds(10, 301, 95, 14);
		lblTriesLeft.setText("Tries Left:5");
		panel.add(lblTriesLeft);

		lblScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblScore.setBounds(267, 301, 82, 14);
		lblScore.setText("Score:0");
		panel.add(lblScore);
		Game();	

	}

	public static int Game()
	{
		Random rand=new Random();
		rnum=rand.nextInt(10)+inc;
		label_1.setText(inc+" and "+(inc+10)+".");		
		inc=inc+10;
		return 0;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		frame = new Main_Game();
		frame.setVisible(true);
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		entered=textField.getText();
		JOptionPane.showMessageDialog(null,rnum);
		JPanel pn=new JPanel();
		JTextField Str_Name = new JTextField(5);
		pn.add(Str_Name);

		int n1=0;
		if(entered.length()!=0 && count!=0)
		{
			n1=Integer.parseInt(entered);
			if(arg0.getSource().equals(button))
			{

				if(n1==rnum)
				{
					JOptionPane.showMessageDialog(null,"Correct Guess!!!");
					score=score+count;
					lblScore.setText("Score:"+String.valueOf(score));
					count=5;
					Game();
					lblTriesLeft.setText("Tries Left:5"); 
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Wrong Guess,Try Again.");
					count--;
					lblTriesLeft.setText("Tries Left:"+String.valueOf(count));
				}
			}

			if(arg0.getSource().equals(button_1))
			{


				if(n1<rnum)
					JOptionPane.showMessageDialog(null,"Too Low");
				else
					JOptionPane.showMessageDialog(null,"Too High");
				score=(float) (score-0.25);
			
			}
		}
		else if(count==0)
		{
			float new_score=score;
			int scr=0;
			
			
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","a" );
				Statement stmt=con.createStatement();  
				String str=("select max(score) as sc from highscore");
				ResultSet rs;
				rs=stmt.executeQuery(str);
				
		        if(!rs.next())
					 JOptionPane.showMessageDialog(null,"Group Id Not Found!");
					else
					{
					   scr=rs.getInt("sc");
					   JOptionPane.showMessageDialog(null,scr);
					}				        
				stmt.executeQuery(str);  		        			        
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,e.getStackTrace());					
				e.printStackTrace();
			}
			if(new_score>scr)
			{	
				JOptionPane.showMessageDialog(null,"Game Over");	
				int result = JOptionPane.showConfirmDialog(null, pn, "Enter Your Name", JOptionPane.OK_CANCEL_OPTION);
				String Name=null;
				if (result == JOptionPane.OK_OPTION) 
					Name= Str_Name.getText();
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","a" );
					Statement stmt=con.createStatement();  
					String str=("Insert into highscore values('"+Name+"','"+score+"')");
					stmt.executeQuery(str);  		        			        
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e.getStackTrace());					
					e.printStackTrace();

				}
			}
			frame.setVisible(false);
			new Home_page().setVisible(true);
		}
		else
			JOptionPane.showMessageDialog(null,"No Numbers Entered");	
	}		
}

