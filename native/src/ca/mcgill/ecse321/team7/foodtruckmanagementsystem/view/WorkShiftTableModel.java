package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import ca.mcgill.ecse321.team7.foodtruckmanagementsystem.model.WorkShift;

/**
 * This model adapts a list of work shift to a JTable.
 * @author Kevin
 *
 */
public class WorkShiftTableModel extends AbstractTableModel {
	
	/**
	 * List of work shifts to be displayed in JTable.
	 */
	private List<WorkShift> data;

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int col) {
		switch(col) {
		case 0: return "Date";
		case 1: return "Start time";
		case 2: return "End time";
		case 3: return "Location";
		default: return null;
		}
	}

	@Override
	public int getRowCount() {
		if (data == null) return 0;
		else return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch(col) {
		case 0: return data.get(row).getWorkDate().toString();
		case 1: return data.get(row).getStartTime().toString();
		case 2: return data.get(row).getEndTime().toString();
		case 3: return data.get(row).getFoodTruck().getLocation();
		default: return null;
		}
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
	
	/**
	 * Changes the list of work shift to the given list.
	 * @param data list of WorkShift to be displayed by JTable/
	 */
	public void setData(List<WorkShift> data) {
		this.data = data;
	}

}
