package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * This class adapts the prototype that was created earlier in the project to a JPanel that is meant to be used as a tab
 * in a JTabbedPane.
 * @author Kevin Laframboise
 *
 */
public class MenuManagementPageAdapter extends JPanel {
	
	/**
	 * The menu management page to be adapted.
	 */
	MenuManagementPage menuManagementPage;
	
	/**
	 * Creates a JPanel with the content pane of a new MenuManagementPage.
	 */
	public MenuManagementPageAdapter() {
		menuManagementPage = new MenuManagementPage();
		setLayout(new BorderLayout());
		add(menuManagementPage.getContentPane(), BorderLayout.CENTER);
	}
	
	/**
	 * This model adapts the inventory of a food truck to a 4 column JTable.
	 * @author Kevin Laframboise
	 *
	 */
	public void refreshData() {
		menuManagementPage.refreshData(true);
	}

}
