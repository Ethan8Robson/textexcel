package textExcel;

public class FormulaCell extends RealCell {
	private String fullText;
	private String abbrText;
	public FormulaCell (String inputText) {
		fullText = inputText;
		abbrText = fullText;
	}
	public String abbreviatedCellText() {
		abbrText = getDoubleValue() + "          ";
		abbrText = abbrText.substring(0,10);
		return abbrText;
	}
	
	public String fullCellText() {
		return fullText;
	}

	public double getDoubleValue() {
		String tempVar = fullText.substring(2, fullText.length()-2);
		String[] splitEquation = tempVar.split(" ");
		double total = Double.parseDouble(splitEquation[0]);
		for (int i = 1; i < splitEquation.length; i += 2) {
			if (splitEquation[i].equals("*")) {
				total *= Double.parseDouble(splitEquation[i + 1]);
			} else if (splitEquation[i].equals("/")) {
				total /= Double.parseDouble(splitEquation[i + 1]);
			} else if (splitEquation[i].equals("+")) {
				total += Double.parseDouble(splitEquation[i + 1]);
			} else if (splitEquation[i].equals("-")) {
				total -= Double.parseDouble(splitEquation[i + 1]);
			}
		}
		return total;
	}
}
