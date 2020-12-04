package ua.itea;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class FileManager {
	Path currentPath = Paths.get("").toAbsolutePath();
	
	public FileManager() {
	}

	public String getCurrentPath() {
		return currentPath.toString();
	}
	
	public boolean cd(String path) {
		if(path.equals(".."))
			currentPath = currentPath.getParent();
		else {
			Path newPath = currentPath.resolve(path);
			if(newPath.toFile().exists())
				currentPath = newPath.normalize();
			else
				return false;
		}
		return true;
	}
	
	public void dir(String path) {
		if(path==null)
			dir(currentPath.toFile());
		else 
			dir( currentPath.resolve(path).toFile() );
	}
	
	static void dir(File file) {

		for(File f: file.listFiles()) {			
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
	        System.out.print(sdf.format(f.lastModified()));
			
			if( f.isDirectory()){
				System.out.print("     <DIR>   ");
			} 
			else if( f.isFile() ) {
				System.out.format(" %10d  ", f.length());
			}
			System.out.println(f.getName());		
		}
	}
	
	public void copy(String pathSrc, String pathDst) throws IOException {
		Path path = currentPath.resolve(pathSrc);
		Path pathTarget = currentPath.resolve(pathDst);
		
		Files.copy(path, pathTarget, StandardCopyOption.REPLACE_EXISTING);
	}
	
	public void type(String path) throws Exception {		
		FileReader fr = new FileReader(currentPath.resolve(path).toFile());
		try (Scanner scan = new Scanner(fr)) {
			while (scan.hasNextLine()) {
				System.out.println(scan.nextLine());
			}
		}		
		fr.close();
	}	
	
}
