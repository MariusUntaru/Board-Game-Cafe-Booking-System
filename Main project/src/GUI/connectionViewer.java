package GUI;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JProgressBar;
import java.awt.Color;

@SuppressWarnings("serial")
public class connectionViewer extends JFrame {

	private JPanel contentPane;
	private JProgressBar progressBar;
	static boolean runVariable = true;
	private boolean internal = true;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					connectionViewer frame = new connectionViewer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws InterruptedException 
	 */
	public connectionViewer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 114, 89);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(Color.GREEN);
		progressBar.setBackground(Color.RED);
		progressBar.setMaximum(1);
		progressBar.setBounds(28, 11, 51, 27);
		contentPane.add(progressBar);
		
		Thread checkConn = new Thread(new Runnable(){
    		public void run()
    		{
    			
		while(internal)
		{
			try {
				progressBar.setValue(Database.connectionChecker.checkconnection());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Database.ConnectionDB.getInstance();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(runVariable == false)
			{
				dispose();
				break;
			}
			
		}

    	}
    		
		
	});


    		
	    	checkConn.start();

}

}
