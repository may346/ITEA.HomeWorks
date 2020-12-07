package ua.itea;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class FilePrinter {
	
	public void ExtFilePrintOut(String fileName) {
		try {
			FileReader fr = null;
			fr = new FileReader(fileName);
			try (Scanner scan = new Scanner(fr)) {
				while (scan.hasNextLine()) {
					System.out.println(scan.nextLine());
				}
			}		
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	public void InnFilePrintOut(String fileName) {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
		try (Scanner scan = new Scanner(is)) {
			while (scan.hasNextLine()) {
				System.out.println(scan.nextLine());
			}
		}		
	}
}
