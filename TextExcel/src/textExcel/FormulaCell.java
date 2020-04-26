package textExcel;
import java.util.*;

public class FormulaCell extends RealCell {
	private String fullText;
	private String abbrText;
	private Spreadsheet sheet;
	// Constructor for FormulaCell
	public FormulaCell (String inputText, Spreadsheet spreadsheet) {
		fullText = inputText;
		abbrText = fullText;
		sheet = spreadsheet;
	}
	
	// Getter for the abbreviated text. Sends in abbrText string, which is originally a copy of fullText into getDoubleValue(). It then truncates the value into a 10 character long string.
	public String abbreviatedCellText() {
		abbrText = getDoubleValue() + "          ";
		abbrText = abbrText.substring(0,10);
		return abbrText;
	}
	
	// Getter for the full text
	public String fullCellText() {
		return fullText;
	}

	// Method that handles all the calculations, returning the double value of the formula. Handles AVG and SUM commands, as well as cell referencing.
	public double getDoubleValue() {
		// Formatting string by getting rid of parentheses and adding a "+ " to beginning of equation for ease of equation solving.
		String equationOnly = "+ " + fullText.substring(2, fullText.length()-2);
		String[] splitEquation = equationOnly.split(" ");
		double total = 0;
		
		// Checker for if it is AVG or SUM command
		if (splitEquation[1].equalsIgnoreCase("avg") || splitEquation[1].equalsIgnoreCase("sum")) {
			splitEquation[2] = splitEquation[2].toUpperCase();
			// Creating new locations for the ranges(min, max) of Cells affected
			Location range1 =  new SpreadsheetLocation(splitEquation[2].substring(0,splitEquation[2].indexOf('-')));
			Location range2 =  new SpreadsheetLocation(splitEquation[2].substring(splitEquation[2].indexOf('-') + 1));
			// Looping through the cells
			for (int i = range1.getCol(); i <= range2.getCol(); i++) {
				for (int j = range1.getRow(); j <= range2.getRow(); j++) {
					Location currentLocation = new SpreadsheetLocation("" + ((char) (i + 'A')) + String.valueOf(j + 1));
					total += (((RealCell) sheet.getCell(currentLocation)).getDoubleValue());
				}
			}
			// Checking if command was "AVG"
			if (splitEquation[1].equalsIgnoreCase("avg")) {
				// Calculating rectangle for number to divide by in average
				int totalNums = (range2.getCol() - range1.getCol() + 1) * (range2.getRow() - range1.getRow() + 1);
				total /= totalNums;
			}
			return total;
		}
		
		// Handling cell references
		for (int i = 1; i < splitEquation.length; i += 2) {
			if (splitEquation[i].charAt(0) - 'A' >= 0) {
				Location referenceLocation = new SpreadsheetLocation(splitEquation[i]);
				splitEquation[i] = ((RealCell) sheet.getCell(referenceLocation)).getDoubleValue() + ""; 
			}
		}
		
		// Solving formula with order of operations. The first for loop checks for any multiplication or division in the loop.
		// For example, if the array was [+,1,-,3,*,4], the array would multiple the -3 and 4, altering the array to [+,1,-,0,-,12]
		for (int i = 0; i < splitEquation.length; i += 2) {
			if (splitEquation[i].equals("*")) {
				splitEquation[i + 1] = (Double.parseDouble(splitEquation[i - 1]) * Double.parseDouble(splitEquation[i + 1])) + "";
				splitEquation[i] = splitEquation[i - 2];
				splitEquation[i - 1] = "0";
			} else if (splitEquation[i].equals("/")) {
				splitEquation[i + 1] = (Double.parseDouble(splitEquation[i - 1]) / Double.parseDouble(splitEquation[i + 1])) + "";
				splitEquation[i] = splitEquation[i-2];
				splitEquation[i - 1] = "0";
			}
		}
		
		// Adding up all the numbers in the array (now that all the numbers are either + or -).
		for (int i = 0; i < splitEquation.length; i += 2) {
			if (splitEquation[i].equals("+")) {
				total += Double.parseDouble(splitEquation[i + 1]);
			} else if (splitEquation[i].equals("-")) {
				total -= Double.parseDouble(splitEquation[i + 1]);
			}
		}
		return total;
	}
}
