package textExcel;

public class ValueCell extends RealCell{
	private String fullText;
	private String abbrText;
	public ValueCell (String inputText) {
		fullText = inputText;
		double tempVar = Math.round(Double.parseDouble(inputText)*1000000000);
		tempVar /= 1000000000;
		String addChars = tempVar + "          ";
		abbrText = addChars.substring(0,10);
	}
	
	public String abbreviatedCellText() {
		return abbrText;
	}
	
	public String fullCellText() {
		return fullText;
	}
	public double getDoubleValue() {
		return Double.parseDouble(fullText);
	}
	
}
