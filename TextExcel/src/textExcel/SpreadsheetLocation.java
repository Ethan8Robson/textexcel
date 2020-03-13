package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location {
	int row;
	int col;
	public SpreadsheetLocation(String cellName) {
		col = cellName.toUpperCase().charAt(0) - 'A';
		row = Integer.parseInt(cellName.substring(1)) - 1;
	}
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}

	@Override
	public int getCol() {
		// TODO Auto-generated method stub
		return col;
	}

}
