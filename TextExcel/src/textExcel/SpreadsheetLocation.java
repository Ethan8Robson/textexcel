package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location {
	int row;
	int col;
	// Constructor that takes in the string cell name and parses it into column and row
	public SpreadsheetLocation(String cellName) {
		col = cellName.toUpperCase().charAt(0) - 'A';
		row = Integer.parseInt(cellName.substring(1)) - 1;
	}
	
	// Getter for rows
	public int getRow() {
		return row;
	}

	// Getter for columns
	@Override
	public int getCol() {
		return col;
	}
	
}
