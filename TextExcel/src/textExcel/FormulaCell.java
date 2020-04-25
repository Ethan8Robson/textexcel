package textExcel;
import java.util.*;

public class FormulaCell extends RealCell {
	private String fullText;
	private String abbrText;
	private Spreadsheet sheet;
	public FormulaCell (String inputText, Spreadsheet spreadsheet) {
		fullText = inputText;
		abbrText = fullText;
		sheet = spreadsheet;
	}
	public String abbreviatedCellText() {
		abbrText = getDoubleValue() + "          ";
		abbrText = abbrText.substring(0,10);
		return abbrText;
	}
	
	public String fullCellText() {
		return fullText;
	}

	public double getDoubleValue() {
		String tempVar = "+ " + fullText.substring(2, fullText.length()-2);
		String[] splitEquation = tempVar.split(" ");
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
		
		// Solving formula
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
