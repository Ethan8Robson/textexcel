package textExcel;

public abstract class RealCell implements Cell {
	public abstract String abbreviatedCellText();
	public abstract String fullCellText();
	public abstract double getDoubleValue();
}
