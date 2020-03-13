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

	@Override
	public String processCommand(String command) {
		// TODO Auto-generated method stub
		String splitCommand[] = command.split(" ", 3);
		if (splitCommand.length == 1 && splitCommand[0].equalsIgnoreCase("clear")) {
			clearAll();
		} else if (splitCommand.length == 2) {
			Location cellLocation = new SpreadsheetLocation(splitCommand[1]);
			clear(cellLocation);
		} else if (splitCommand.length == 3) {
			Location cellLocation =  new SpreadsheetLocation(splitCommand[0]);
			sheet[cellLocation.getRow()][cellLocation.getCol()] = new TextCell(splitCommand[2]);
		} else {
			Location cellLocation = new SpreadsheetLocation(splitCommand[0]);
			return sheet[cellLocation.getRow()][cellLocation.getCol()].fullCellText();
		}
		return getGridText();
	}

	@Override
	public int getRows() {
		// TODO Auto-generated method stub
		return numRows;
	}

	@Override
	public int getCols() {
		// TODO Auto-generated method stub
		return numCols;
	}

	@Override
	public Cell getCell(Location loc) {
		// TODO Auto-generated method stub
		return sheet[loc.getRow()][loc.getCol()];
	}

	@Override
	public String getGridText() {
		// TODO Auto-generated method stub
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
	
	public void clear(Location loc) {
		sheet[loc.getRow()][loc.getCol()] = new EmptyCell();
	}
	
	public void clearAll() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j <numCols; j++) {
				sheet[i][j] = new EmptyCell();
			}
		}
	}

}
