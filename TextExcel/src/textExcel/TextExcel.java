package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel {

	public static void main(String[] args) {
		// Add your command loop here
		String decision;
		String inputLine;
		do {
			Scanner userInput = new Scanner(System.in);
			Spreadsheet sheet = new Spreadsheet();
			System.out.println("What would you like to do?");
			inputLine = userInput.nextLine();
			String gridText = sheet.processCommand(inputLine);
			System.out.print(gridText);
			System.out.println("Do you want to quit? Type \"quit\" to quit");
			decision = userInput.nextLine();
		} while (!decision.toUpperCase().equals("QUIT"));
		//TestsALL.Helper th = new TestsALL.Helper();
		//System.out.println(sheet.getGridText());
	} 
}
