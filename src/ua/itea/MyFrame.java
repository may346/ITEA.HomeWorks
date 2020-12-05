package ua.itea;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String saveFileName = "local.bin"; 
			
	private JLabel label1 = new JLabel();
	private JLabel label2 = new JLabel();
	private JLabel label3 = new JLabel();
	
	private JButton buttonEn;
	private JButton buttonDe;
	private JButton buttonUa;
	
	private JButton buttonSave = new JButton();
	private JButton buttonLoad = new JButton();
	
	String language = "ua";
	String country = "UA";
	
	void	setLabelsText() {
		Locale locale = new Locale(language, country);	
		ResourceBundle resourceBundle = ResourceBundle.getBundle("MessageBundle", locale);
		
		label1.setText(resourceBundle.getString("l1"));
		label2.setText(resourceBundle.getString("l2"));
		label3.setText(resourceBundle.getString("l3"));
		
		buttonSave.setText(resourceBundle.getString("b1"));
		buttonLoad.setText(resourceBundle.getString("b2"));
	}
	
	void	saveLocale() {		
		try(DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(saveFileName))){
			
			outputStream.writeUTF(language);
			outputStream.writeUTF(country);
			
		} catch (IOException e) {
		    JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Save error",
		            JOptionPane.ERROR_MESSAGE);
		}
	}
	
	void  loadLocale() {
		try(DataInputStream inputStream = new DataInputStream(new FileInputStream(saveFileName))){
			
			language = inputStream.readUTF();
			country = inputStream.readUTF();
			
			setLabelsText();
			
		} catch (IOException e) {
		    JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Load error",
		            JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public MyFrame() {
		super("HomeWork02");
		
		setLabelsText();
			
		buttonUa = createButton(new ImageIcon("images/ua.gif"), "ua", "UA");
		buttonEn = createButton(new ImageIcon("images/us50.gif"), "en", "EN");
		buttonDe = createButton(new ImageIcon("images/de.gif"), "de", "DE");
		
		buttonSave.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveLocale();
			}
		});		
		buttonLoad.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadLocale();
			}
		});
		
		setLayout(new GridLayout(3, 3));
		
		add(buttonUa);
		add(buttonEn);
		add(buttonDe);

		add(label1);
		add(label2);
		add(label3);
		
		add(buttonSave);
		add(buttonLoad);
		
		setSize(600, 300);	setLocation(100, 100);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private JButton createButton(Icon icon, String inLanguage, String inCountry) {
		JButton button = new JButton(inCountry, icon);		
		button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				language = inLanguage;
				country = inCountry;
				
				setLabelsText();
			}
		});		
		return button;
	}
}
