package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel {

	public static void main(String[] args) {
		// Creating spreadsheet and fields for user input
		String decision;
		String inputLine;
		Spreadsheet sheet = new Spreadsheet();
		// Command loop for user input, loops until user types "quit"
		do {
			Scanner userInput = new Scanner(System.in);
			System.out.println("What would you like to do?");
			inputLine = userInput.nextLine();
			String gridText = sheet.processCommand(inputLine);
			System.out.print(gridText);
			System.out.println("Do you want to quit? Type \"quit\" to quit");
			decision = userInput.nextLine();
		} while (!decision.toUpperCase().equals("QUIT"));
	} 
}
