package ua.itea;

import java.awt.GridLayout;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class App extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private static final String innerTextFileName = "in.txt"; 
	private static final String externTextFileName = "out.txt"; 
	private static final String innerPictureFileName = "in.jpg"; 
	private static final String externPictureFileName = "out.jpg"; 
	
	public App() 
	{
		super("HomeWork05");
		setLayout(new GridLayout(2,2));
				
		//-----inner data-------
		ClassLoader classLoader = this.getClass().getClassLoader();
		URL urlPicture = classLoader.getResource(innerPictureFileName);		
		add(new JLabel(new ImageIcon(urlPicture)));
		
		InputStream is = classLoader.getResourceAsStream(innerTextFileName);		
		try {
//			add(new JLabel(
//					new	String( is.readAllBytes() )
//					));
			JTextArea taInn = new JTextArea(
						new	String( is.readAllBytes() ));
			taInn.setEditable(false);
			add(taInn);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//-----external data-------
		add(new JLabel(new ImageIcon(externPictureFileName)));
		try {
//			add(new JLabel(
//					new	String( Files.readAllBytes(Paths.get(externTextFileName)) )
//			));
			JTextArea taExt = new JTextArea(
					new	String( Files.readAllBytes(Paths.get(externTextFileName)) ));
			taExt.setEditable(false);
			add(taExt);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setSize(600, 300);	setLocation(100, 100);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
    public static void main( String[] args )
    {
    	new App();
    }
}
