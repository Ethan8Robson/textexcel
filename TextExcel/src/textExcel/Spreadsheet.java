package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid {
	int numRows;
	int numCols;
	Cell[][] sheet;
	
	public Spreadsheet(int row, int col) {
		numRows = row;
		numCols = col;
		sheet = new Cell[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				sheet[i][j] = new EmptyCell();
			}
		}
	}
	
	public Spreadsheet() {
		this(20, 12);
	}

	// Process command line, handles the command by parsing the string command.
	@Override
	public String processCommand(String command) {
		// TODO Auto-generated method stub
		String splitCommand[] = command.split(" ", 3);
		splitCommand[0].toUpperCase();
		if (splitCommand.length == 1 && splitCommand[0].equalsIgnoreCase("clear")) {
			// clearing the whole grid
			clearAll();
		} else if (splitCommand.length == 2) {
			// clearing specified location
			Location cellLocation = new SpreadsheetLocation(splitCommand[1]);
			clear(cellLocation);
		} else if (splitCommand.length == 3) {
			// Cell assignment
			Location cellLocation = new SpreadsheetLocation(splitCommand[0]);
			if (splitCommand[2].substring(0,1).equals("\"")) {
				sheet[cellLocation.getRow()][cellLocation.getCol()] = new TextCell(splitCommand[2]);
			} else if (splitCommand[2].contains("%")) {
				sheet[cellLocation.getRow()][cellLocation.getCol()] = new PercentCell(splitCommand[2]);
			} else if (splitCommand[2].contains("(")) {
				sheet[cellLocation.getRow()][cellLocation.getCol()] = new FormulaCell(splitCommand[2], this);
			} else {
				sheet[cellLocation.getRow()][cellLocation.getCol()] = new ValueCell(splitCommand[2]);
			}
		} else {
			// Cell inspection
			Location cellLocation = new SpreadsheetLocation(splitCommand[0]);
			return sheet[cellLocation.getRow()][cellLocation.getCol()].fullCellText();
		}
		return getGridText();
	}

	// Getter for the amount of rows
	@Override
	public int getRows() {
		return numRows;
	}

	// Getter for the amount of columns
	@Override
	public int getCols() {
		return numCols;
	}

	// Getter for cells, takes in a location and returns the cell at specified location
	@Override
	public Cell getCell(Location loc) {
		return sheet[loc.getRow()][loc.getCol()];
	}

	// Getter for the grid. Stores the whole grid in a string and returns it
	@Override
	public String getGridText() {
		String grid = "   |";
		char colCharacter = 'A';
		for (int i = 0; i < numCols; i++) {
			grid += colCharacter + "         |";
			colCharacter += 1;
		}
		for (int i = 0; i < numRows; i++) {
			grid += "\n";
			if (i < 9) {
				grid += i+1 + "  |";
			} else {
				grid += i+1 + " |";
			}
			for (int j = 0; j < numCols; j++) {
				grid += sheet[i][j].abbreviatedCellText() + "|";
			}
		}
		grid += "\n";
		return grid;
	}
	
	// Method for clear command, takes in a location and clears specified location, replacing it with an empty cell
	public void clear(Location loc) {
		sheet[loc.getRow()][loc.getCol()] = new EmptyCell();
	}
	
	// Clear all method, replaces all cells in sheet with empty cells
	public void clearAll() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j <numCols; j++) {
				sheet[i][j] = new EmptyCell();
			}
		}
	}

}
