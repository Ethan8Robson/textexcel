package textExcel;

public class TextCell implements Cell {
	private String textString;
	private String abbrTextString;
	
	// Constructor for TextCell
	public TextCell(String stringInput) {
		textString = stringInput;
		String noQuotes = stringInput.substring(1, stringInput.length()-1) + "          ";
		abbrTextString = noQuotes.substring(0, 10);
	}
	
	// Getter for abbreviated text
	public String abbreviatedCellText() {
		return abbrTextString.substring(0,10);
	}
	
	// Getter for full text
	public String fullCellText() {
		return textString;
	}
}
