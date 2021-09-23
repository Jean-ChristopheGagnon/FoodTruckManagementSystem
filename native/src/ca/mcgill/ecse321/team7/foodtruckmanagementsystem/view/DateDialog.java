package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

/**
 * This dialog prompts the user to input a date. The input date can be retrieved as a java.sql.Date object.
 * @author Kevin Laframboise
 *
 */
public class DateDialog extends JDialog {

	// Components
	private final JPanel contentPanel = new JPanel();
	private JLabel lblPickADate;
	private JDatePickerImpl datePicker;
	private DateFormatter dateFormatter;
	
	// Data fields
	/**
	 * The entered date.
	 */
	private Date date;

	/**
	 * Launch the application. Do not use. For testing purposes only.
	 */
	public static void main(String[] args) {
		try {
			DateDialog dialog = new DateDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a DateDialog and populates the date picker with the given date.
	 * @param date to be populated in the date picker.
	 */
	public DateDialog(java.sql.Date date) {
		this.date = new Date(date.getTime());
		init();
	}
	
	/**
	 * Creates a DateDialog with no pre-populated date.
	 */
	public DateDialog() {
		date = null;
		init();
	}

	/**
	 * Create the dialog. Code generated by Window Builder.
	 */
	public void init() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Edit date");
		
		// set ui to native
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		// Setup date picker
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		dateFormatter = new DateFormatter();
		datePicker = new JDatePickerImpl(datePanel, dateFormatter);
		
		if(date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			try {
				datePicker.getJFormattedTextField().setText(dateFormatter.valueToString(calendar));
			} catch (ParseException e) {
			}
		}
		
		setBounds(100, 100, 328, 130);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblPickADate = new JLabel("Edit date:");
		}
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPickADate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(125, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPickADate)
						.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(177, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						okButtonActionPerformed();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
	}

	protected void okButtonActionPerformed() {
		
		// Get date and times
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime((Date) dateFormatter.stringToValue(datePicker.getJFormattedTextField().getText()));
			date = cal.getTime();
		} catch (ParseException e) {
		}
		
		// Close dialog once date is retrieved
		dispose();
		
	}
	
	/**
	 * @return the date that was inputed. 
	 */
	public java.sql.Date getDate() {
		return new java.sql.Date(date.getTime());
	}
	
}
