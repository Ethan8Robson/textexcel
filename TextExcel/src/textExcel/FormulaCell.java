package textExcel;

public class FormulaCell extends RealCell {
	private String fullText;
	private String abbrText;
	public FormulaCell (String inputText) {
		fullText = inputText;
		abbrText = fullText;
//		String tempVar = inputText.substring(0, inputText.length());
//		String[] splitEquation = tempVar.split(" ");
//		for (int i = 0; i < splitEquation.length; i+=2) {
//			if ()
//		}
//		
	}
	
	public String abbreviatedCellText() {
		return abbrText;
	}
	
	public String fullCellText() {
		return fullText;
	}
	
	public double getDoubleValue() {
		return 1;
	}
}
