package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					Menu frame = new Menu();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBookTable = new JButton("Book table");
		btnBookTable.setBounds(148, 60, 113, 23);
		contentPane.add(btnBookTable);
		btnBookTable.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent ae){
			  
				  BookingWindow bookingWindow = new BookingWindow();
				  bookingWindow.setVisible(true);
				  dispose();
			  }
			  
			  });
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(162, 116, 89, 23);
		contentPane.add(btnExit);
		btnExit.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent ae){
				  connectionViewer.runVariable = false;
				  
				  dispose();
				  
			  }
			  
			  });
	}
}
