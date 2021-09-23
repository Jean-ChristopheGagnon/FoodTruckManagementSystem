package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JScrollPane;
import java.awt.Dimension;

/**
 * This JFrame contains a JTabbedPane that aggregates all tabs of our management system.
 * @author Kevin Laframboise
 *
 */
public class FoodTruckManagerPage extends JFrame {

	private JPanel contentPane;
	private FoodTruckManagementTab foodTruckManagementTab;
	private MenuManagementPageAdapter menuManagementPage;
	private StaffManagementTab staffManagementTab;
	private SuppliesManagementTab suppliesManagementTab;
	private OrderManagementTab orderManagementTab;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	 * Create the frame.
	 */
	public FoodTruckManagerPage() {
		setTitle("Food Truck Management System");
		
		// set ui to native
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		// Start window builder generated code
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Food Truck Management", null, scrollPane_1, null);
		
		foodTruckManagementTab = new FoodTruckManagementTab();
		scrollPane_1.setViewportView(foodTruckManagementTab);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Menu Management", null, scrollPane, null);
		
		menuManagementPage = new MenuManagementPageAdapter();
		scrollPane.setViewportView(menuManagementPage);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("Staff Management", null, scrollPane_2, null);
		
		staffManagementTab = new StaffManagementTab();
		scrollPane_2.setViewportView(staffManagementTab);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane.addTab("Supplies Management", null, scrollPane_3, null);
		
		suppliesManagementTab = new SuppliesManagementTab();
		scrollPane_3.setViewportView(suppliesManagementTab);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		tabbedPane.addTab("Order Management", null, scrollPane_4, null);
		
		orderManagementTab = new OrderManagementTab();
		orderManagementTab.setPreferredSize(new Dimension(693, 195));
		scrollPane_4.setViewportView(orderManagementTab);
		
		// End window builder generated code
		
		// Adding a change listener to make sure all data is refreshed when a tab is opened.
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// Refresh data of the selected tab when tab changed
				int tab = ((JTabbedPane)arg0.getSource()).getSelectedIndex();
				switch(tab) {
				case 0: foodTruckManagementTab.refreshData();
				case 1: menuManagementPage.refreshData();
				case 2: staffManagementTab.refreshData();
				case 3: suppliesManagementTab.refreshData();
				case 4: orderManagementTab.refreshData();
				}
			}
			
		});
		
		
	}

}
