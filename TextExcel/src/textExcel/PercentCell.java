package textExcel;

public class PercentCell extends RealCell {
	private String fullText;
	private String abbrText;
	// Constructor for PercentCell
	public PercentCell (String inputText) {
		fullText = (Double.parseDouble(inputText.substring(0,inputText.length()-1)))/100 + "";
		String tempVar =  inputText.substring(0, inputText.indexOf("."));
		if (tempVar.length() < 9) {
			tempVar += "%          ";
			abbrText = tempVar.substring(0,10);
		} else {
			abbrText = tempVar.substring(0,9) + "%";
		}
	}
	
	// Getter for abbreviated text
	public String abbreviatedCellText() {
		return abbrText;
	}
	
	// Getter for fulltext
	public String fullCellText() {
		return fullText;
	}
	
	// Getter for the double value
	public double getDoubleValue() {
		return Integer.parseInt(fullText);
	}
}
