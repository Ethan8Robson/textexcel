package textExcel;
import java.util.*;

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
		String tempVar = "+ " + fullText.substring(2, fullText.length()-2);
		String[] splitEquation = tempVar.split(" ");
		double total = 0;
		for (int i = 0; i < splitEquation.length; i += 2) {
			if (splitEquation[i].equals("*")) {
				splitEquation[i + 1] = (Double.parseDouble(splitEquation[i - 1]) * Double.parseDouble(splitEquation[i + 1])) + "";
				splitEquation[i] = splitEquation[i - 2];
				splitEquation[i - 1] = "0";
			} else if (splitEquation[i].equals("/")) {
				splitEquation[i + 1] = (Double.parseDouble(splitEquation[i - 1]) / Double.parseDouble(splitEquation[i + 1])) + "";
				splitEquation[i] = splitEquation[i-2];
				splitEquation[i - 1] = "0";
			}
		}
		for (int i = 0; i < splitEquation.length; i += 2) {
			if (splitEquation[i].equals("+")) {
				total += Double.parseDouble(splitEquation[i + 1]);
			} else if (splitEquation[i].equals("-")) {
				total -= Double.parseDouble(splitEquation[i + 1]);
			}
		}
		return total;
	}
}
