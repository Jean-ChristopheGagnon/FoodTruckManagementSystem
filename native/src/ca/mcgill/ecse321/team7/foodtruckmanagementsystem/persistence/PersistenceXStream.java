package ca.mcgill.ecse321.team7.foodtruckmanagementsystem.persistence;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.thoughtworks.xstream.XStream;

/**
 * Class provided in Assignment 1, ECSE231 Fall 2016
 */
public class PersistenceXStream {
	/**
	 * Instance of an XStream object.
	 */
	private static XStream xstream = new XStream();
	/**
	 * Filename. data.xml by default
	 */
	private static String filename = "data.xml";

	/**
	 * Saves the given object to an XML file using XStream.
	 * @param obj to be saved
	 * @return true for successful operation, false otherwise.
	 */
	public static boolean saveToXMLwithXStream(Object obj) {
		xstream.setMode(XStream.ID_REFERENCES);
		String xml = xstream.toXML(obj); // save our xml file
		try {
			FileWriter writer = new FileWriter(filename);
			writer.write(xml);
			writer.close();
			return true;
		} catch (IOException e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * Reads an object from a XML file that was written with XStream.
	 * @return the read object.
	 */
	public static Object loadFromXMLwithXStream() {
		xstream.setMode(XStream.ID_REFERENCES);
		try {
			FileReader fileReader = new FileReader(filename); // load our xml file
			return xstream.fromXML(fileReader);
		} catch (IOException e) {
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * Sets a xml tag to be used when saving the given class.
	 * @param xmlTagName the name of the xml tag.
	 * @param className the class using the given tab.
	 */
	public static void setAlias(String xmlTagName, Class<?> className) {
		xstream.alias(xmlTagName, className);
	}

	/**
	 * Sets the filename to the given name.
	 * @param fn new filename
	 */
	public static void setFilename(String fn) {
		filename = fn;
	}
}