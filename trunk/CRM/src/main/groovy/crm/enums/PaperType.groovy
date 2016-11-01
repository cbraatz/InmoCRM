package crm.enums

public enum PaperType {
	A(0, 0),//Ilimitado
	B(210, 297),//A4
	O(220, 340),//Oficio
	P(216, 330)//Oficio 2
	private final int width;
	private final int heigth;
	public PaperType(int width, int heigth) {
		this.width=width;
		this.heigth=heigth;
	}
	/*public boolean getValue(){
		return this.value;
	}*/
}
