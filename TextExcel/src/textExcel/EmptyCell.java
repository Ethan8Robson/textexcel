package textExcel;

public class EmptyCell implements Cell {
	// Constructor
	public EmptyCell() {
	}

	// Getter for the abbreviated text
	public String abbreviatedCellText() {
		// text for spreadsheet cell display, must be exactly length 10
		return "          ";
	}
	
	// Getter for the full text
	public String fullCellText() {
		// text for individual cell inspection, not truncated or padded
		return "";
	}
}
