package crm

final class SearchAttribute {
	String attribute;
	boolean stringAttribute;
	public SearchAttribute(String attribute) {
		this.attribute=attribute;
		this.stringAttribute=true;
	}
	public SearchAttribute(String attribute, boolean isStringAttribute) {
		this.attribute=attribute;
		this.stringAttribute=isStringAttribute;
	}
	public String getAttribute() {
		return attribute;
	}
	public boolean isStringAttribute() {
		return this.stringAttribute;
	}
}