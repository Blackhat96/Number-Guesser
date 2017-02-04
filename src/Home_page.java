import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class Home_page extends JFrame implements ActionListener{
	JButton btnNewGame = new JButton("New Game");
	JButton btnHighScore = new JButton("High Score");
	JButton btnHelp = new JButton("Help");
	
	private JPanel contentPane;
	private final JLabel lblIcon = new JLabel("icon");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Home_page frame = new Home_page();
	frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Home_page() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Guess a Random Number");
		setBounds(455,250, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		btnNewGame.addActionListener(this);			
		
		btnNewGame.setBounds(162, 57, 101, 25);
		contentPane.add(btnNewGame);
		
		btnHighScore.addActionListener(this);
		btnHighScore.setBounds(162, 93, 101, 25);
		contentPane.add(btnHighScore);
		
		btnHelp.addActionListener(this);
		btnHelp.setBounds(162, 131, 101, 23);
		contentPane.add(btnHelp);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		btnExit.setBounds(162, 166, 101, 23);
		contentPane.add(btnExit);
		lblIcon.setIcon(new ImageIcon("C:\\Users\\Abhishek\\Desktop\\randomnumber.png"));
		lblIcon.setBounds(0, 0, 434, 261);
		
		contentPane.add(lblIcon);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnNewGame))
		{
			Main_Game l = new Main_Game();	
			l.setVisible(true);
		}
		if(e.getSource().equals(btnHighScore))
		{
	new Highscore().setVisible(true);
		
		}
		if(e.getSource().equals(btnHelp))
		{
			String msg="Enter a Random number in the given  range, You have 5 chances to guess the correct number."+"\n"+"If you guess the number Correctly the range will increase."+"\n"+
		"GOOD LUCK :)";
			JOptionPane.showMessageDialog(null,msg,"HELP",JOptionPane.INFORMATION_MESSAGE);
		}
		}

}
