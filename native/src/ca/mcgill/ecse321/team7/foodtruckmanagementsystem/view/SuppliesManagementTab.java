package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.view;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.DuplicateTypeException;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.FoodTruckController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.FoodTruckControllerAdapter;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.SETypesController;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.controller.SETypesControllerAdapter;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.EquipmentType;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruck;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.FoodTruckManagementSystem;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.Supply;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.SupplyType;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;

/**
 * This panel, meant to be used as a tab in a JTabbedPane, allows the user to interact with
 * data associated with supplies.
 * @author Kevin Laframboise
 *
 */
public class SuppliesManagementTab extends JPanel {
	
	// Components
	private JTextField nameField;
	private JTextField quantityField;
	private JTable suppliesTable;
	private JLabel errorLabel;
	private JComboBox<String> editSupplyComboBox;
	private JRadioButton equipmentRadioButton;
	private JRadioButton ingredientRadioButton;
	private JComboBox<String> addSupplyFoodTruckComboBox;
	private JComboBox<String> addSupplyComboBox;
	private JComboBox<String> manageFoodTruckComboBox;
	
	// Data fields
	/**
	 * Return code used to indicate that an object is of the type SuppluType.
	 */
	public static final int SUPPLY_TYPE_FLAG = 0;
	/**
	 * Return code used to indicate that an object is of the type EquipmentType.
	 */
	public static final int EQUIPMENT_TYPE_FLAG = 1;
	/**
	 * Model used to adapt the list of supplies to a JTable.
	 */
	private InventoryTableModel dataModel;
	/**
	 * Index of the selected FoodTruck in the "Add to FoodTruck" JComboBox.
	 */
	private int selectedAddToFoodTruck;
	/**
	 * Index of the selected Supply in the "Supply to Edit" JComboBox.
	 */
	private int selectedSupplyToEdit;
	/**
	 * Index of the selected Supply in the "Supply to Add" JComboBox.
	 */
	private int selectedSupplyToAdd;
	/**
	 * Index of the selected Food Truck in the "View Inventory" JComboBox.
	 */
	private int selectedFoodTruckToManage;
	/**
	 * String containing any error messages.
	 */
	private String error;
	
	/**
	 * Initializes the components. Code generated using Eclipse's Window Builder plugin.
	 */
	public SuppliesManagementTab() {
		// Start generated code
		setLayout(new BorderLayout(0, 0));
		
		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		add(errorLabel, BorderLayout.SOUTH);
		
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel_4.add(panel);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Supplies Management", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Create New Supply", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Edit Supply Type", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Add Supply to Truck", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
					.addGap(20))
		);
		
		JLabel lblFoodTruck = new JLabel("Food truck:");
		
		addSupplyFoodTruckComboBox = new JComboBox<String>();
		addSupplyFoodTruckComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				selectedAddToFoodTruck = cb.getSelectedIndex();
			}
		});
		
		JLabel lblSupply = new JLabel("Supply:");
		
		addSupplyComboBox = new JComboBox<String>();
		addSupplyComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				selectedSupplyToAdd = cb.getSelectedIndex();
			}
		});
		
		JLabel lblQuantity = new JLabel("Quantity:");
		
		quantityField = new JTextField();
		quantityField.setColumns(10);
		
		JButton addToTruckButton = new JButton("Add to Truck");
		addToTruckButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToTruckButtonActionPerformed();
			}
		});
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addComponent(lblFoodTruck)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(addSupplyFoodTruckComboBox, 0, 271, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_7.createSequentialGroup()
					.addComponent(lblSupply)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(addSupplyComboBox, 0, 466, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblQuantity)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(quantityField, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
				.addComponent(addToTruckButton, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFoodTruck)
						.addComponent(addSupplyFoodTruckComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupply)
						.addComponent(addSupplyComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(quantityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuantity))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(addToTruckButton)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_7.setLayout(gl_panel_7);
		
		editSupplyComboBox = new JComboBox<String>();
		editSupplyComboBox.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				selectedSupplyToEdit = cb.getSelectedIndex();
			}
		});
		
		JButton editButton = new JButton("Edit...");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editButtonActionPerformed();
			}
		});
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteButtonActionPerformed();
			}
		});
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(editSupplyComboBox, 0, 590, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(editButton)
							.addGap(12))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(deleteButton, GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(editSupplyComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(editButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(deleteButton)
					.addGap(0, 0, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblTypeName = new JLabel("Supply name: ");
		
		nameField = new JTextField();
		nameField.setColumns(20);
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createButtonActionPerformed();
			}
		});
		
		JLabel lblType = new JLabel("Type:");
		
		equipmentRadioButton = new JRadioButton("Equipment");
		
		ingredientRadioButton = new JRadioButton("Ingredient");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(1)
					.addComponent(lblTypeName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblType)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(equipmentRadioButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ingredientRadioButton)
					.addGap(8)
					.addComponent(createButton)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTypeName)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(createButton)
						.addComponent(lblType)
						.addComponent(equipmentRadioButton)
						.addComponent(ingredientRadioButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		panel.setLayout(gl_panel);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Inventory Management", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.add(panel_5);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton editQtyButton = new JButton("Edit Quantity...");
		editQtyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editQtyButtonActionPerformed();
			}
		});
		
		JButton editPurchaseButton = new JButton("Edit Date...");
		editPurchaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editPurchaseButtonActionPerformed();
			}
		});
		
		JButton removeButton = new JButton("Remove From Truck");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeButtonActionPerformed();
			}
		});
		
		JLabel lblFoodTruck_1 = new JLabel("Food truck:");
		
		manageFoodTruckComboBox = new JComboBox<String>();
		manageFoodTruckComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				selectedFoodTruckToManage = cb.getSelectedIndex();
			}
		});
		
		JButton viewButton = new JButton("View Inventory");
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewButtonActionPerformed();
			}
		});
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(lblFoodTruck_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(manageFoodTruckComboBox, 0, 458, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(viewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(removeButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 113, Short.MAX_VALUE)
						.addComponent(editQtyButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
						.addComponent(editPurchaseButton, GroupLayout.PREFERRED_SIZE, 134, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFoodTruck_1)
						.addComponent(manageFoodTruckComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(viewButton))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(11)
							.addComponent(editQtyButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(editPurchaseButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(removeButton)
							.addContainerGap())
						.addGroup(gl_panel_5.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))))
		);
		
		suppliesTable = new JTable();
		suppliesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		suppliesTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(suppliesTable);
		panel_5.setLayout(gl_panel_5);
		// End generated code
		
		// Initialize button group
		ButtonGroup typeButtons = new ButtonGroup();
		typeButtons.add(equipmentRadioButton);
		typeButtons.add(ingredientRadioButton);
		ingredientRadioButton.setSelected(true);
		
		// Initialize table
		dataModel = new InventoryTableModel();
		suppliesTable.setModel(dataModel);
		
	}
	
	protected void removeButtonActionPerformed() {
		
		// Initialize controller
		FoodTruckController ftc = new FoodTruckControllerAdapter();

		// Get selected food truck
		FoodTruck foodTruck = dataModel.getFoodTruck();

		// Get supply/equipment info
		int selectedRow = suppliesTable.getSelectedRow();
		boolean isEquipment = dataModel.processSelectedRow(selectedRow).x == EQUIPMENT_TYPE_FLAG;
		int index = dataModel.processSelectedRow(selectedRow).y;
		
		// Get selected object
		Object supply = null;
		try {
			supply = (isEquipment)? foodTruck.getEquipment(index) : foodTruck.getSupply(index);
		} catch (Exception e) {
		}
		
		// Delete from truck
		try {
			if (isEquipment)
				ftc.removeEquipment(foodTruck, (Equipment) supply, ((Equipment) supply).getQuantity());
			else
				ftc.removeSupply(foodTruck, (Supply) supply);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		refreshData();
		
		
		
	}
	protected void editPurchaseButtonActionPerformed() {

		// Initialize controller
		FoodTruckController ftc = new FoodTruckControllerAdapter();

		// Get selected food truck
		FoodTruck foodTruck = dataModel.getFoodTruck();

		// Get supply/equipment info
		int selectedRow = suppliesTable.getSelectedRow();
		boolean isEquipment = dataModel.processSelectedRow(selectedRow).x == EQUIPMENT_TYPE_FLAG;
		int index = dataModel.processSelectedRow(selectedRow).y;
		
		if(!isEquipment) {
			error = "Purchase date for ingredients cannot be changed!";
			refreshData();
			return;
		}
		
		// Get equipment object
		Equipment equipment = null;
		try {
			equipment = foodTruck.getEquipment(index);
		} catch (Exception e) {
		}
		
		// Get new date
		Date newDate = null;
		if (index >= 0 && equipment != null) {
			DateDialog dateDialog = new DateDialog(equipment.getPurchaseDate());
			dateDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dateDialog.setVisible(true);
			newDate = dateDialog.getDate();
		}
		
		// Change date
		try {
			ftc.changeEquipmentPurchaseDate(equipment, newDate);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		refreshData();
		
		
	}
	protected void editQtyButtonActionPerformed() {
		
		// Initialize controller
		FoodTruckController ftc = new FoodTruckControllerAdapter();
		
		// Get selected food truck
		FoodTruck foodTruck = dataModel.getFoodTruck();
		
		// Get supply/equipment info
		int selectedRow = suppliesTable.getSelectedRow();
		boolean isEquipment = dataModel.processSelectedRow(selectedRow).x == EQUIPMENT_TYPE_FLAG;
		int index = dataModel.processSelectedRow(selectedRow).y;
		
		String input = null;
		if (index >= 0) {
			// Prompt user for new qty
			String promptMsg = "Enter new quantity for "
					+ ((isEquipment) ? foodTruck.getEquipment(index).getEquipmentType().getName()
							: foodTruck.getSupply(index).getSupplyType().getName())
					+ ": ";
			input = JOptionPane.showInputDialog(this, promptMsg, "Edit quantity", JOptionPane.QUESTION_MESSAGE);
		}
		
		//Parse qty
		double qty = -1;
		try {
			qty = Double.parseDouble(input);
		} catch (NullPointerException | NumberFormatException e) {
		}
		
		Object supply = null;
		try {
			supply = (isEquipment)? foodTruck.getEquipment(index) : foodTruck.getSupply(index);
		} catch (Exception e) {
		}
		
		// Change qty
		try {
			if(isEquipment) ftc.changeEquipmentQunatity((Equipment) supply, qty);
			else ftc.changeSupplyQuantity((Supply) supply, qty);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		refreshData();
		
	}
	protected void viewButtonActionPerformed() {
		refreshData();
	}
	protected void addToTruckButtonActionPerformed() {
		
		// Get instance of management system
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		// Initialize controller
		FoodTruckController ftc = new FoodTruckControllerAdapter();
		
		// Get food truck and qty
		FoodTruck foodTruck = null;
		double qty = -1;
		try {
			foodTruck = ftms.getFoodTruck(selectedAddToFoodTruck);
		} catch (IndexOutOfBoundsException e) {
		}
		try {
		qty = Double.parseDouble(quantityField.getText());
		} catch (NullPointerException | NumberFormatException e) {
		}
		
		// Get type to add
		boolean isEquipment = processSelectedType(selectedSupplyToAdd).x == EQUIPMENT_TYPE_FLAG;
		int index = processSelectedType(selectedSupplyToAdd).y;
		
		// Add type to food truck
		try {
			if(isEquipment) ftc.createEquipment(foodTruck, ftms.getEquipmentType(index), qty, new Date(System.currentTimeMillis()));
			else ftc.createSupply(foodTruck, ftms.getSupplyType(index), qty);
		} catch (InvalidInputException | DuplicateTypeException e) {
			error = e.getMessage();
		}
		
		refreshData();
		
		
	}
	protected void deleteButtonActionPerformed() {
		
		// Verify user wishes to proceed with deletion
		String confMsg = "Are you sure you wish to delete this supply? It will be removed from every food truck's inventory"
				+ " and menu items to which it was associated.";
		if(JOptionPane.showConfirmDialog(this, confMsg, "Delete supply type", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
			return;
		}
		
		// Get instance of management system
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		// Initialize controller
		SETypesController setc = new SETypesControllerAdapter();
		
		// Get info on selected type
		boolean isEquipment = processSelectedType(selectedSupplyToEdit).x == EQUIPMENT_TYPE_FLAG;
		int index = processSelectedType(selectedSupplyToEdit).y;
		
		// Delete selected type
		try {
			if(isEquipment) setc.deleteEquipmentType(ftms.getEquipmentType(index));
			else setc.deleteSupplyType(ftms.getSupplyType(index));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		refreshData();
		
		
	}
	protected void editButtonActionPerformed() {
		
		//TODO include in report that EquipmentType and SupplyType should both extend an abstract "Type" class, it would make this whole much more efficient. 
		
		// Get instance of management system
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		// Initialize controller
		SETypesController setc = new SETypesControllerAdapter();
		
		// Get selected type
		String supplyName = null;
		SupplyType sType = null;
		EquipmentType eType = null;
		boolean isEquipment = processSelectedType(selectedSupplyToEdit).x == EQUIPMENT_TYPE_FLAG;
		int index = processSelectedType(selectedSupplyToEdit).y;
		
		if(isEquipment) {
			// Get equipment type
			eType = ftms.getEquipmentType(index);
			// Get equipment type name 
			supplyName = eType.getName();
		}
		else {
			// Get supply type
			sType = ftms.getSupplyType(index);
			// Get supply type name
			supplyName = sType.getName();
		}
		
		// Get new name
		String newName = null;
		if (sType != null || eType != null) {
			newName = JOptionPane.showInputDialog(this, "Enter the new name for " + supplyName + ": ", "Edit supply type", JOptionPane.QUESTION_MESSAGE);
		}
		
		// Change the name
		try {
			if(isEquipment) setc.changeEquipmentTypeName(eType, newName);
			else setc.changeSupplyTypeName(sType, newName);
		} catch (InvalidInputException | DuplicateTypeException e) {
			error = e.getMessage();
		}
		
		refreshData();
		return;
	}
	protected void createButtonActionPerformed() {
		
		// Initialize controller
		SETypesController setc = new SETypesControllerAdapter();
		
		// Get given name
		String typeName = nameField.getText();
		
		// Create type depending on selected radio button
		try {
			if(equipmentRadioButton.isSelected()) setc.createEquipmentType(typeName);
			else setc.createSupplyType(typeName);
		} catch (InvalidInputException | DuplicateTypeException e) {
			error = e.getMessage();
		}
		
		refreshData();
		
	}
	
	/**
	 * Refreshes all components that display data to the most up-to-date instance of the FoodTruckManagementSystem.
	 */
	public void refreshData() {
		// if an error was detected, show error to user and do nothing
		if (error == null) {
			error = "";
		}
		errorLabel.setText(error);
		if (error.trim().length() > 0) {
			error = "";
			return;
		}
		
		// Get instance of management system
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();		
		
		// Populate combo boxes
		// Populate supply combo boxes
		String prev = (String) editSupplyComboBox.getSelectedItem();
		String prev1 = (String) addSupplyComboBox.getSelectedItem();
		Object supply;
		String name;
		editSupplyComboBox.removeAllItems();
		addSupplyComboBox.removeAllItems();
		Iterator<SupplyType> its = ftms.getSupplyTypes().iterator();
		while(its.hasNext()) {
			supply = its.next();
			name = ((SupplyType) supply).getName();
			editSupplyComboBox.addItem("Ingredient - " + name);
			addSupplyComboBox.addItem("Ingredient - " + name);
		}
		Iterator<EquipmentType> ite = ftms.getEquipmentTypes().iterator();
		while(ite.hasNext()) {
			supply = ite.next();
			name = ((EquipmentType) supply).getName();
			editSupplyComboBox.addItem("Equipment - " + name);
			addSupplyComboBox.addItem("Equipment - " + name);
		}
		if(prev != null) editSupplyComboBox.setSelectedItem(prev);
		if(prev1 != null) addSupplyComboBox.setSelectedItem(prev1);
		
		// Populate food trucks combo boxes
		prev = (String) addSupplyFoodTruckComboBox.getSelectedItem();
		prev1 = (String) manageFoodTruckComboBox.getSelectedItem();
		addSupplyFoodTruckComboBox.removeAllItems();
		manageFoodTruckComboBox.removeAllItems();
		Iterator<FoodTruck> itf = ftms.getFoodTrucks().iterator();
		FoodTruck ft;
		while(itf.hasNext()) {
			ft = itf.next();
			addSupplyFoodTruckComboBox.addItem(ft.getLocation());
			manageFoodTruckComboBox.addItem(ft.getLocation());
		}
		if(prev != null) addSupplyFoodTruckComboBox.setSelectedItem(prev);
		if(prev1 != null) manageFoodTruckComboBox.setSelectedItem(prev1);
		
		// Refresh data for table
		if (ftms.numberOfFoodTrucks() > 0 && selectedFoodTruckToManage >= 0) {
			dataModel.setData(ftms.getFoodTruck(selectedFoodTruckToManage));
			dataModel.fireTableChanged(null);
		}
		
		// Reset text fields
		nameField.setText("");
		quantityField.setText("");
	}
	
	/**
	 * Returns a point object containing information about the selected object in the combo box.
	 * @param selectedTypeIndex ComboBox in which the selected object is located.
	 * @return a point object with x co-ord indicating whether the object is a Supply or Equipment
	 * type and y co-ord, its index in its respective list in the FTMS.
	 */
	private Point processSelectedType(int selectedTypeIndex) {
		
		// Get instance of management system
		FoodTruckManagementSystem ftms = FoodTruckManagementSystem.getInstance();
		// Prepare return object
		Point attributes = null;
		
		/* Because we populate the supplies before the ingredients, if the index is larger
		 * than the number of supply types, it must be an equipment type.
		 */
		if(selectedTypeIndex >= ftms.numberOfSupplyTypes()) {
			// Get index of equipment type in ftms list
			selectedTypeIndex -= ftms.numberOfSupplyTypes();
			attributes = new Point(EQUIPMENT_TYPE_FLAG, selectedTypeIndex);
		}
		else {
			attributes = new Point(SUPPLY_TYPE_FLAG, selectedTypeIndex);
		}
		return attributes;
	}
	
	/**
	 * This model adapts the inventory of a food truck to a 4 column JTable.
	 * @author Kevin Laframboise
	 *
	 */
	class InventoryTableModel extends AbstractTableModel {
		
		/**
		 * FoodTruck for which the inventory is to be displayed.
		 */
		private FoodTruck ft;
		
		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public int getRowCount() {
			if(ft == null) return 0;
			return ft.numberOfEquipment() + ft.numberOfSupplies();
		}

		@Override
		public Object getValueAt(int row, int col) {
			
			boolean isEquipment = processSelectedRow(row).x == EQUIPMENT_TYPE_FLAG;
			int index = processSelectedRow(row).y;
			
			switch(col) {
			case 0: return (isEquipment)? "Equipment" : "Ingredient";
			case 1: return (isEquipment)? ft.getEquipment(index).getEquipmentType().getName() : ft.getSupply(index).getSupplyType().getName();
			case 2: return String.format("%.2f", (isEquipment)? ft.getEquipment(index).getQuantity() : ft.getSupply(index).getQuantity());
			case 3: return (isEquipment)? ft.getEquipment(index).getPurchaseDate().toString() : "N/A";
			default: return null;
			}
		}
		
		@Override
		public String getColumnName(int col) {
			switch(col) {
			case 0: return "Type";
			case 1: return "Name";
			case 2: return "Quantity";
			case 3: return "Purchase date";
			default: return null;
			}
		}

		/**
		 * Changes to food truck for which the inventory is to be displayed to the given FoodTruck.
		 * @param ft new FoodTruck for which the inventory is to be displayed.
		 */
		public void setData(FoodTruck ft) {
			this.ft = ft;
		}
		
		/** 
		 * @return the food truck containing the data used to display associated JTables.
		 */
		public FoodTruck getFoodTruck() {
			return ft;
		}
		
		/**
		 * Returns a point object containing information about the selected object in the combo box.
		 * @param row in which the object is located
		 * @return a point object with x co-ord indicating whether the object is a Supply or Equipment
		 * type and y co-ord, its index in its respective list in the FoodTruck.
		 */
		public Point processSelectedRow(int row) {
			
			int isEquipment = SUPPLY_TYPE_FLAG;
			int index = row;
			
			if(row >= ft.numberOfSupplies()) {
				index -= ft.numberOfSupplies();
				isEquipment = EQUIPMENT_TYPE_FLAG;
			}
			
			return new Point(isEquipment, index);
		}
		
	}

}
