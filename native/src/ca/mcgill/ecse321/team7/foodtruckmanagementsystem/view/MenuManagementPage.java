package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.MenuController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.MenuControllerAdapter;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Menu;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.MenuItem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence.PersistenceFoodTruckManager;

/**
 * This panel, meant to be used as a tab in a JTabbedPane, allows the user to interact with
 * data associated with menus.
 * @author Kevin Laframboise
 *
 */
public class MenuManagementPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6875305927375689054L;

	// views
	private JPanel contentPane;
	private JTextField newMenuNameField;
	private JTextField changeMenuNameField;
	private JTextField newMenuItemNameField;
	private JTextField newMenuItemPriceField;
	private JTextField updatedItemName;
	private JTextField updatedItemPrice;
	private JTable menuTable;
	private JComboBox<String> menuComboBox;
	private JComboBox<String> menuItemComboBox;
	private JLabel errorLabel;

	// data fields
	private int currentMenu;
	private int selectedMenu;
	private int selectedMenuItem;
	String error = "";
	private MenuTableModel dataModel;

	/**
	 * Launch the application. Do not use. For testing purposes only. Launch
	 * through FoodTruckManager instead.
	 */
	public static void main(String[] args) {
		PersistenceFoodTruckManager.loadTruckManagerModel("foodtruckmanager.xml");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodTruckManagerPage frame = new FoodTruckManagerPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. Code generated using Eclipse's WindowBuilder plugin.
	 */
	public MenuManagementPage() {
		// set ui to native
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		// start auto generated code by windowbuilder
		setTitle("Food Truck Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewMenuName = new JLabel("New menu name: ");

		newMenuNameField = new JTextField();
		newMenuNameField.setColumns(10);

		JButton btnAddMenu = new JButton("Add menu");
		btnAddMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addMenuButtonActionPerformed();
			}
		});

		JLabel lblEditMenu = new JLabel("Edit menu:");

		menuComboBox = new JComboBox<>();
		menuComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				selectedMenu = cb.getSelectedIndex();
			}
		});

		changeMenuNameField = new JTextField();
		changeMenuNameField.setColumns(10);

		JButton btnChangeName = new JButton("Change name");
		btnChangeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeMenuNameButtonActionPerformed();
			}
		});

		JSeparator separator = new JSeparator();

		JLabel lblNewItem = new JLabel("Item name:");

		newMenuItemNameField = new JTextField();
		newMenuItemNameField.setColumns(10);

		JLabel lblNewItemPrice = new JLabel("Price:");

		newMenuItemPriceField = new JTextField();
		newMenuItemPriceField.setColumns(10);

		JButton btnAddItem = new JButton("Add item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMenuItemButtonActionPerformed();
			}
		});

		JLabel lblRemoveItem = new JLabel("Edit item:");

		menuItemComboBox = new JComboBox<>();
		menuItemComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				selectedMenuItem = cb.getSelectedIndex();
			}
		});

		JButton btnRemoveItem = new JButton("Remove item");
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMenuItemButtonActionPerformed();
			}
		});

		JButton btnDeleteMenu = new JButton("Delete menu");
		btnDeleteMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteMenuButtonActionPerformed();
			}
		});

		JLabel lblNewName = new JLabel("New name:");

		JButton btnVieweditMenu = new JButton("View/Edit menu");
		btnVieweditMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewButtonActionPerformed();
			}
		});

		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);

		JSeparator separator_1 = new JSeparator();

		JLabel lblNewName_1 = new JLabel("New name:");

		updatedItemName = new JTextField();
		updatedItemName.setColumns(10);

		JLabel lblNewPrice = new JLabel("Price:");

		updatedItemPrice = new JTextField();
		updatedItemPrice.setColumns(10);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateButtonActionPerformed();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnEditSelectedItems = new JButton("Edit Selected Item's Ingredients...");
		btnEditSelectedItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editItemButtonActionPerformed();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
						.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewMenuName)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(newMenuNameField, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewItem)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(newMenuItemNameField, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewItemPrice)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(newMenuItemPriceField, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblEditMenu)
										.addComponent(lblNewName))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(menuComboBox, 0, 192, Short.MAX_VALUE)
										.addComponent(changeMenuNameField, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnVieweditMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnChangeName, GroupLayout.PREFERRED_SIZE, 105, Short.MAX_VALUE)
								.addComponent(btnAddItem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAddMenu, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)))
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnEditSelectedItems)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(errorLabel, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDeleteMenu))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblRemoveItem)
									.addGap(18)
									.addComponent(menuItemComboBox, 0, 216, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewName_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(updatedItemName, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewPrice)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(updatedItemPrice, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnRemoveItem, GroupLayout.PREFERRED_SIZE, 104, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(newMenuNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddMenu)
						.addComponent(lblNewMenuName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(menuComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVieweditMenu)
						.addComponent(lblEditMenu))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(changeMenuNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnChangeName)
						.addComponent(lblNewName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(newMenuItemNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewItem))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(newMenuItemPriceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewItemPrice))
						.addComponent(btnAddItem))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRemoveItem)
						.addComponent(menuItemComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRemoveItem))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewName_1)
							.addComponent(updatedItemName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewPrice)
							.addComponent(updatedItemPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnUpdate))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDeleteMenu)
						.addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditSelectedItems)))
		);
		// end WindowBuilder generated code

		// init table
		menuTable = new JTable();
		dataModel = new MenuTableModel();
		menuTable.setModel(dataModel);
		menuTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(menuTable);
		contentPane.setLayout(gl_contentPane);

		// refresh visual
		refreshData(true);
	}

	protected void editItemButtonActionPerformed() {
		
		// Get instance of system
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		
		// Get selected menu
		Menu menu = null;
		try {
			menu = ftms.getFoodList(selectedMenu);
		} catch (NullPointerException | IndexOutOfBoundsException e) {
		}
		// Get selected item
		MenuItem item = null;
		try {
			item = menu.getMenuItem(menuTable.getSelectedRow());
		} catch (NullPointerException | IndexOutOfBoundsException e) {
		}
		
		// Display dialog
		EditItemDialog editItemDialog;
		try {
			editItemDialog = new EditItemDialog(item);
			editItemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			editItemDialog.setVisible(true);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		refreshData(false);
		
	}

	protected void viewButtonActionPerformed() {
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		currentMenu = selectedMenu;
		dataModel.setData(ftms.getFoodList(currentMenu).getMenuItems());
		dataModel.fireTableChanged(null);
		refreshData(false);
	}

	protected void updateButtonActionPerformed() {

		// check if new name or new price was provided, if not display a message
		// to user
		if ((updatedItemName.getText() == null || updatedItemName.getText().length() <= 0)
				&& (updatedItemPrice.getText() == null || updatedItemPrice.getText().length() <= 0)) {
			error = "You need to enter a new name or a new price!";
			refreshData(false);
			return;
		}

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		MenuController mc = new MenuControllerAdapter();

		// updated name if new name provided
		if (updatedItemName.getText() != null && updatedItemName.getText().length() > 0) {
			System.out.println("Name not null");
			try {
				mc.changeMenuItemName(ftms.getFoodList(currentMenu),
						ftms.getFoodList(currentMenu).getMenuItem(selectedMenuItem), updatedItemName.getText());
			} catch (InvalidInputException e) {
				error = e.getMessage();
				refreshData(false);
				return;
			}
		}

		// update price if new price provided
		if (updatedItemPrice.getText() != null && updatedItemPrice.getText().length() > 0) {
			try {
				int price = (int) (Float.parseFloat(updatedItemPrice.getText()) * 100);
				mc.changeMenuItemPrice(ftms.getFoodList(currentMenu),
						ftms.getFoodList(currentMenu).getMenuItem(selectedMenuItem), price);
			} catch (NumberFormatException e) {
				error = "Invalid price entered!";
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}

		// refresh date
		dataModel.fireTableChanged(null);
		refreshData(false);

	}

	protected void deleteMenuButtonActionPerformed() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		MenuController mc = new MenuControllerAdapter();
		
		if(ftms.getFoodList().size() == 0) {
			error = "There are no menu left to delete!";
			refreshData(false);
			return;
		}
		// confirm with user
		int choice = JOptionPane.showConfirmDialog(null,
				"Are you sure you wish to delete menu " + ftms.getFoodList(currentMenu).getName()
						+ "? This operation is irreversible",
				"Delete menu " + ftms.getFoodList(currentMenu).getName(), JOptionPane.YES_NO_OPTION);

		// delete menu if user chose yes
		if (choice == JOptionPane.YES_OPTION) {
			try {
				mc.deleteMenu(ftms.getFoodList(currentMenu));
				selectedMenu = currentMenu = 0;
				dataModel.setData(null);
				dataModel.fireTableChanged(null);
				refreshData(true);
			} catch (InvalidInputException e1) {
				error = e1.getMessage();
			}
		}

		// refresh data
		refreshData(false);

	}

	protected void removeMenuItemButtonActionPerformed() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		MenuController mc = new MenuControllerAdapter();

		try {
			mc.deleteMenuItem(ftms.getFoodList(currentMenu), ftms.getFoodList(currentMenu).getMenuItem(selectedMenuItem));
		} catch (InvalidInputException ex) {
			error = ex.getMessage();
		} catch (IndexOutOfBoundsException ex1) {
			// do nothing
		}

		// refresh data
		dataModel.fireTableChanged(null);
		refreshData(false);

	}

	protected void addMenuItemButtonActionPerformed() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		MenuController mc = new MenuControllerAdapter();
		int price = 0;

		try {
			price = (int) (Float.parseFloat(newMenuItemPriceField.getText()) * 100);
		} catch (Exception ex) {

		}

		try {
			mc.createMenuItem(ftms.getFoodList(currentMenu), newMenuItemNameField.getText(), price);
		} catch (InvalidInputException ex) {
			error = ex.getMessage();
		}

		dataModel.fireTableChanged(null);
		refreshData(false);

	}

	protected void changeMenuNameButtonActionPerformed() {

		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		MenuController mc = new MenuControllerAdapter();

		try {
			mc.changeMenuName(ftms.getFoodList(currentMenu), changeMenuNameField.getText());
		} catch (InvalidInputException ex) {
			error = ex.getMessage();
		}

		// refresh data
		refreshData(true);

	}

	protected void addMenuButtonActionPerformed() {

		MenuController mc = new MenuControllerAdapter();

		try {
			mc.createMenu(newMenuNameField.getText());
		} catch (InvalidInputException ex) {
			error = ex.getMessage();
		}

		// refresh data
		refreshData(true);

	}

	/**
	 * Refreshes all data outside of the menu item table.
	 * 
	 * @param updateMenuList
	 *            specifies whether or not the list of menu should be updated.
	 */
	public void refreshData(boolean updateMenuList) {

		// if an error was detected, show error to user and do nothing
		if (error == null) {
			error = "";
		}
		errorLabel.setText(error);
		if (error.trim().length() > 0) {
			error = "";
			return;
		}
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();

		// update menu combo box, if needed
		if (updateMenuList) {
			menuComboBox.removeAllItems();
			Iterator<Menu> itm = ftms.getFoodList().iterator();
			while (itm.hasNext()) {
				menuComboBox.addItem(itm.next().getName());
			}
			// Update table model
			if(ftms.getFoodList().size() > 0) {
				dataModel.setData(ftms.getFoodList(currentMenu).getMenuItems());
			}
		}
		// update menu item combo box
		menuItemComboBox.removeAllItems();
		if(ftms.getFoodList().size() > 0) {
			Iterator<MenuItem> iti = ftms.getFoodList(currentMenu).getMenuItems().iterator();
			while (iti.hasNext()) {
				menuItemComboBox.addItem(iti.next().getName());
			}
		}

		// reset all text fields
		newMenuNameField.setText("");
		changeMenuNameField.setText("");
		newMenuItemNameField.setText("");
		newMenuItemPriceField.setText("");
		updatedItemName.setText("");
		updatedItemPrice.setText("");

	}
}
