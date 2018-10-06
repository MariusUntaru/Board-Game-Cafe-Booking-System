package GUI;

import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controllers.BookingCtr;
import Controllers.GameTableCtr;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Button;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class TableSearchWindow extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private static JTextField boardGameHolder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableSearchWindow frame = new TableSearchWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public static void setBoardGame(String name)
	{
		boardGameHolder.setText(name);
	}
	
	
	public TableSearchWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1108, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(263, 13, 827, 400);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"TableID", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "01", "02"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	    table.setPreferredScrollableViewportSize(new Dimension(500,800));
		table.setFillsViewportHeight(true);
		table.getModel();
		scrollPane.setViewportView(table);
		
		JButton SearchTable = new JButton("Search");
		SearchTable.setBounds(55, 313, 158, 25);
		contentPane.add(SearchTable);
		
		JLabel label = new JLabel("Amount of players:");
		label.setBounds(10, 229, 118, 16);
		contentPane.add(label);
		
		JLabel lblAmountOfHours = new JLabel("Amount of hours:");
		lblAmountOfHours.setBounds(10, 203, 118, 16);
		contentPane.add(lblAmountOfHours);
		
		JLabel label_3 = new JLabel("Date:");
		label_3.setBounds(10, 174, 56, 16);
		contentPane.add(label_3);
		
		JTextPane DateText = new JTextPane();
		DateText.setBounds(134, 174, 97, 16);
		contentPane.add(DateText);
		
		JLabel label_4 = new JLabel("Booking information:");
		label_4.setBounds(12, 122, 138, 16);
		contentPane.add(label_4);
		
		JButton BoardgameButton = new JButton("Find a boardgame");
		BoardgameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BoardGameSearchWindow boardgame;
				try {
					boardgame = new BoardGameSearchWindow();
					boardgame.setVisible(true); 

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		BoardgameButton.setBounds(40, 17, 158, 25);
		contentPane.add(BoardgameButton);
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(10, 151, 56, 16);
		contentPane.add(lblArea);
		
		Choice PlayerBox = new Choice();
		PlayerBox.setBounds(134, 229, 97, 16);
		contentPane.add(PlayerBox);
		PlayerBox.add("0");
		PlayerBox.add("1");
		PlayerBox.add("2");
		PlayerBox.add("3");
		PlayerBox.add("4");
		PlayerBox.add("5");
		PlayerBox.add("6");
		PlayerBox.add("7");
		PlayerBox.add("8");
		
		Choice HourBox = new Choice();
		HourBox.setBounds(134, 201, 97, 16);
		contentPane.add(HourBox);
		HourBox.add("0");
		HourBox.add("1");
		HourBox.add("2");
		HourBox.add("3");
		HourBox.add("4");
		HourBox.add("5");
		HourBox.add("6");
		HourBox.add("7");
		HourBox.add("8");
		HourBox.add("9");
		HourBox.add("10");
		HourBox.add("11");
		HourBox.add("12");
		
		
		Choice AreaBox = new Choice();
		AreaBox.setBounds(134, 146, 97, 16);
		contentPane.add(AreaBox);
		AreaBox.add("Upstairs");
        AreaBox.add("Downstairs");
		
		boardGameHolder = new JTextField();
		boardGameHolder.setBounds(26, 55, 187, 20);
		contentPane.add(boardGameHolder);
		boardGameHolder.setColumns(10);
		
		Button addBookingButton = new Button("Add selection to booking");
		addBookingButton.setEnabled(false);
		addBookingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int amountOfPlayersTextInput =  Integer.parseInt(PlayerBox.getSelectedItem());
				String DateInput = DateText.getText();
				int startHour = table.getSelectedColumn() + 13;
				int endHour = startHour - 1 + Integer.parseInt(HourBox.getSelectedItem());
				
				BookingCtr.createNewBooking(amountOfPlayersTextInput, DateInput, startHour, endHour, table.getSelectedRow());

				dispose();
				
				
			}
		});
		addBookingButton.setBounds(55, 370, 158, 22);
		contentPane.add(addBookingButton);
		
		
		SearchTable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			String area = null;
			DateText.getText();
			model.setRowCount(0);

				String areaInputText = AreaBox.getSelectedItem();
				
				if (!"both".equalsIgnoreCase(areaInputText) && !"".equalsIgnoreCase(areaInputText))
				{
				area = areaInputText;
				}				
				int amountOfPlayersTextInput =  Integer.parseInt(PlayerBox.getSelectedItem());
				
				String DateInput = DateText.getText();
				if (!"date".equalsIgnoreCase(DateInput) && !"".equalsIgnoreCase(DateInput))
				{
					DateText.getText();
				}
				 try {

					GameTableCtr.searchGameTable(area, amountOfPlayersTextInput);
					GameTableCtr.addBookingData(DateText.getText());

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			  }
			  
		
	    });
	
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            DefaultTableModel model = (DefaultTableModel) table.getModel();
				String date = DateText.getText();

	        	
	        	for(int c = 0; c < table.getColumnCount(); c++)
	        	{
	        		for(int r = 0; r < table.getRowCount(); r++)
	        		{
						model.setValueAt(" ", r, c);
	        		}
	        		
	        	}
	        	
	        	for(int i = 0; i < GameTableCtr.getGameTableList().size(); i++)
				{
				model.setValueAt(GameTableCtr.getGameTableList().get(i).getGameTableID(), i, 0);
				}	
	        	
	        	
	        	GameTableCtr.addBookingData(date);
	        
	        	
	        	
	        	if (!model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString().equalsIgnoreCase("booked"))
	        	{
				model.setValueAt("Book?", table.getSelectedRow(), table.getSelectedColumn());
        		boolean warningboxActivated = false;


	        	for(int i = 0; i < HourBox.getSelectedIndex(); i++)
				{
		        	if (!model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()+i).toString().equalsIgnoreCase("booked") && warningboxActivated == false)
		        	{
					model.setValueAt("Book?", table.getSelectedRow(), table.getSelectedColumn()+i);
					addBookingButton.setEnabled(true);

		        	}
		        	else if (warningboxActivated == true)
		        	{
					addBookingButton.setEnabled(false);

		        	}
		        	else
		        	{
		        		Object frame = null;
						warningboxActivated = true;
						addBookingButton.setEnabled(false);

						JOptionPane.showMessageDialog((Component) frame, "Those hours are already taken, try to select some other time");
						
		        	}

				}
	        	
	        	}
	        	else
	        	{
	        		Object frame = null;
					JOptionPane.showMessageDialog((Component) frame, "Those hours are already taken, try to select some other time");

	        	}
	        }
	        
	    });

		
	}	
	public static void resetRowNumber()
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
	}
	
	public static void addRowsFromGameTableList(int gameTableID)
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{gameTableID});
	
	}
	
	public static void addBookingStates(int startHour, int endHour, int i)
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		if(startHour > 0){
		model.setValueAt("Booked", i, startHour);
		model.setValueAt("Booked", i, endHour);
		
		for(int j = 0; j < endHour - startHour; j++)
		{
			model.setValueAt("Booked", i, startHour + j);

		}
		}
	}
}
