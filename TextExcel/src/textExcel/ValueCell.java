package textExcel;

public class ValueCell extends RealCell{
	private String fullText;
	private String abbrText;
	
	// Constructor for ValueCell
	public ValueCell (String inputText) {
		fullText = inputText;
		double tempVar = Math.round(Double.parseDouble(inputText)*1000000000);
		tempVar /= 1000000000;
		String addChars = tempVar + "          ";
		abbrText = addChars.substring(0,10);
	}
	
	// Getter for abbreviated text
	public String abbreviatedCellText() {
		return abbrText;
	}
	
	// Getter for full text
	public String fullCellText() {
		return fullText;
	}
	
	// Getter for the double value
	public double getDoubleValue() {
		return Double.parseDouble(fullText);
	}
	
}
