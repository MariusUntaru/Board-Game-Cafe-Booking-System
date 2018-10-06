package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controllers.BookingCtr;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class BookingWindow extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JTextField txtPhoneNumber;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingWindow frame = new BookingWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public BookingWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(92, 181, 650, 39);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Customer", "Boardgame", "Date", "Table nr."
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	    table.setPreferredScrollableViewportSize(new Dimension(500,800));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		JButton findPerson = new JButton("Search");
		findPerson.setBounds(196, 109, 158, 25);
		contentPane.add(findPerson);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setText("Phone number");
		txtPhoneNumber.setBounds(196, 60, 158, 22);
		contentPane.add(txtPhoneNumber);
		
		JLabel lblNewLabel = new JLabel("Find customer");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(196, 13, 158, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblFindATable = new JLabel("Find table");
		lblFindATable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFindATable.setBounds(508, 18, 132, 25);
		contentPane.add(lblFindATable);
		
		JButton findTable = new JButton("Search");
		findTable.setBounds(464, 109, 158, 25);
		contentPane.add(findTable);
		
		JButton Bookbutton = new JButton("BOOK!");
		Bookbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
				try {
					BookingCtr.addBookingToDatabase();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Menu menu = new Menu();
				menu.setVisible(true);
				
				dispose();
				
			}
		});
		Bookbutton.setBounds(92, 247, 650, 93);
		contentPane.add(Bookbutton);
		
		findTable.addActionListener(new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e)
			{ TableSearchWindow f = new TableSearchWindow();
			f.setVisible(true); 
			}		
	});
		
		findPerson.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent ae){
			  String input = null;
			  input = txtPhoneNumber.getText();
			  String inputText = null;
			  
			  
			  if (!"Phone number".equalsIgnoreCase(input) && !"".equalsIgnoreCase(input))
			  {
				  inputText = txtPhoneNumber.getText();

			  
			  
			  try {
				Controllers.BookingCtr.addPerson(inputText);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			  }
			  
			   table.setValueAt(BookingCtr.getSelectedPerson().getName(), 0, 0);
			   	        	
	        }
	    });

	}

	public static void setBoardGameAndBookingInfo(String boardgameName, String bookingDate, int gameTableID) {
		
		table.setValueAt(boardgameName, 0, 1);

		table.setValueAt(bookingDate, 0, 2);
		table.setValueAt(gameTableID, 0, 3);
		
	}
}
