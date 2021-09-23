package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * This class formats calendar objects in formatted string and vice versa.
 * @author Kevin Laframboise
 *
 */
public class DateFormatter extends AbstractFormatter {

	/**
	 * Format of the Strings.
	 */
	private String dPattern = "yyyy-MM-dd";
	/**
	 * Date formatter.
	 */
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(dPattern);

	@Override
	public Object stringToValue(String text) throws ParseException {
		Object value = dateFormatter.parseObject(text);
		return value;
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		Calendar cal = null;
		try {
			if (value != null) {
				cal = (Calendar) value;
			}
		} catch(Exception e) {
			
		}
		
		return (cal == null)? "" : dateFormatter.format(cal.getTime());
		
	}

}
