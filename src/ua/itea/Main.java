package ua.itea;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		FileManager fm = new FileManager();
		//System.out.println("Current path is: " + fm.getCurrentPath());
		
		String inputString;
		String command; 
		String param1;
		String param2;
		boolean finish=false;
		
		System.out.println("Enter command (cd, dir, cp, type, exit) :" );
		
		try (Scanner scaner = new Scanner(System.in).useDelimiter("\n");) {
			
			while(!finish) {
				System.out.println(fm.getCurrentPath() + ">");
				
				inputString = scaner.nextLine();
				inputString = inputString.trim();
				if(inputString.length()==0)
					continue;
				
				try (Scanner s = new Scanner(inputString);) {
					command = s.next();
					if(s.hasNext()) 
						param1 = s.next();
					else
						param1 = null;							
	
					if(s.hasNext()) 
						param2 = s.nextLine().trim();
					else
						param2 = null;
				}
				
				try {
					
					switch(command) {
					case "cd":
						if( param1!=null ) {
							if( !fm.cd(param1) )
								System.out.println("Path isn't exist");
						}
						else
							System.out.println("Command 'cd' need one parameter");										
						break;
					case "dir":
						fm.dir(param1);
						break;
					case "cp":
						if( param2!=null )
							fm.copy(param1, param2);
						else
							System.out.println("Command 'cp' need two parameters");												
						break;
					case "type":
						if( param1!=null )
							fm.type(param1);
						else
							System.out.println("Command 'type' need one parameter");										
						break;
					case "exit":
						finish =  true;
						break;
					default:
						System.out.println("You entered: " + inputString );					
						System.out.println("Wrong command. Enter command (cd, dir, cp, type, exit) :" );					
					}
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}			
		}
		System.out.println("----FINISH------" );
	}	
}
