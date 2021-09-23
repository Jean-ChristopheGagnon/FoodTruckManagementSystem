package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.MenuItem;

/**
 * Model to adapt the list of menu items to a 2 column table.
 */
public class MenuTableModel extends AbstractTableModel {

	/**
	 * The list of MenuItems to be displayed.
	 */
	private List<MenuItem> data;
	/**
	 * Column names.
	 */
	private String[] keys = { "Name", "Price" };

	/**
	 * Creates a model for the given list of MenuItems.
	 * @param data to represented in the table.
	 */
	public MenuTableModel(List<MenuItem> data) {
		this.data = data;
	}

	/**
	 * Creates a model with an empty data list.
	 */
	public MenuTableModel() {

	}

	/**
	 * Sets the list of MenuItems to be displayed.
	 * @param data list containing the menu items to be displayed.
	 */
	public void setData(List<MenuItem> data) {
		this.data = data;
	}

	@Override
	public String getColumnName(int col) {
		if (col >= 2)
			return null;
		return keys[col];
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return (data == null)? 0 : data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		if (row == 0 && data.size() == 0) {
			return 0;
		}
		if (row >= data.size())
			return null;

		if (col == 0) {
			return data.get(row).getName();
		} else if (col == 1) {
			return String.format("%.2f", data.get(row).getPrice() / 100.0);
		} else
			return null;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}