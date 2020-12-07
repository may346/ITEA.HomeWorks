package ua.itea;

public class Main {
	
	static final String INN_FILENAME = "macros.txt";
	static final String OUT_FILENAME = "README.txt";

	public static void main(String[] args) {
		
		FilePrinter fp = new FilePrinter();
		
		System.out.println("Inner file (" + INN_FILENAME + "):");
		fp.InnFilePrintOut(INN_FILENAME);
		
		System.out.println("Extern file (" + OUT_FILENAME + "):");
		fp.ExtFilePrintOut(OUT_FILENAME);
	}

}
