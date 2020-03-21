package textExcel;

public class PercentCell extends RealCell {
	private String fullText;
	private String abbrText;
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
	public String abbreviatedCellText() {
		return abbrText;
	}
	public String fullCellText() {
		return fullText;
	}
	public double getDoubleValue() {
		return Integer.parseInt(fullText);
	}
}
