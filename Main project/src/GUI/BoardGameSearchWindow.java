package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Controllers.BoardGameCtr;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Choice;
import java.awt.Button;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class BoardGameSearchWindow extends JFrame {
	private static JTable table;
	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardGameSearchWindow frame = new BoardGameSearchWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	

	
	
	public BoardGameSearchWindow() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(221, 11, 650, 400);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Size", "Players Max", "Players min", "Category", "BoardGameID"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	    table.setPreferredScrollableViewportSize(new Dimension(500,800));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		TextField NameInput = new TextField();
		NameInput.setText("name");
		NameInput.setBounds(41, 46, 174, 22);
		contentPane.add(NameInput);
		
		TextField AmountOfPlayersInput = new TextField();
		AmountOfPlayersInput.setBounds(41, 91, 174, 22);
		AmountOfPlayersInput.setText("0");
		contentPane.add(AmountOfPlayersInput);
		
		Choice SizeChoice = new Choice();
		SizeChoice.setBounds(41, 140, 174, 20);
		contentPane.add(SizeChoice);
		SizeChoice.add("Both");
		SizeChoice.add("Big");
		SizeChoice.add("Small");
		
		Choice Categories = new Choice();
		Categories.setBounds(41, 186, 174, 20);
		contentPane.add(Categories);
		Categories.add("None");
		Categories.add("Horror");
		Categories.add("Strategy");
		Categories.add("Environmental");
		Categories.add("Family");
		Categories.add("Abstract");
		Categories.add("Thematic");
		

		
		Button button = new Button("SEARCH!");
		button.setBounds(41, 220, 174, 79);
		contentPane.add(button);
		
		Button button_1 = new Button("Pick Board Game!");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BoardGameCtr.setBoardGame(table.getSelectedRow());
			
				dispose();
			}
		});
		button_1.setBounds(41, 324, 174, 79);
		contentPane.add(button_1);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 26, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblPlayerAmount = new JLabel("Player amount");
		lblPlayerAmount.setBounds(10, 74, 87, 14);
		contentPane.add(lblPlayerAmount);
		
		JLabel lblGameSize = new JLabel("Game size");
		lblGameSize.setBounds(10, 119, 68, 14);
		contentPane.add(lblGameSize);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(10, 166, 46, 14);
		contentPane.add(lblCategory);
		
		
		button.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent ae){
				
				String name = null;
				int amountPlayers = 0;
				String size = null;
				String category = null;
				
				String inputText = NameInput.getText();
				if (!"name".equalsIgnoreCase(inputText) && !"".equalsIgnoreCase(inputText))
				{
					name = NameInput.getText();
				}
				int amountOfPlayersTextInput = 0;
				
				try{
				amountOfPlayersTextInput = Integer.parseInt(AmountOfPlayersInput.getText());
				}
				catch (NumberFormatException ex){
				amountOfPlayersTextInput = 0;
				}
				
				if (amountOfPlayersTextInput > 0)
				{
				amountPlayers = Integer.parseInt(AmountOfPlayersInput.getText());
				}
				
				String sizeInputText = SizeChoice.getSelectedItem();
				
				
				if (!"both".equalsIgnoreCase(sizeInputText))
				{
				size = sizeInputText;
				}
				
				String categoryInputText = Categories.getSelectedItem();
				if(!"None".equalsIgnoreCase(categoryInputText))
				{
				category = categoryInputText;
				}
				
				try {
					BoardGameCtr.searchBoardGamesByMultiple(name, size, category, amountPlayers);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
				
			});
		
	}
	
	public static void resetRowNumber()
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		model.setRowCount(0);
	}
	
	public static void addRowsFremBoardGameList(String name, String size, int maxAmountOfPlayers, int minAmountOfPlayers, String category, int boardGameID)
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		model.addRow(new Object[]{name, size, maxAmountOfPlayers,minAmountOfPlayers,category,boardGameID});
	
	}
}
