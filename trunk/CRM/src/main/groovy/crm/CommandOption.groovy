package crm

class CommandOption {
	boolean selected;
	Object item;
	public CommandOption(Object item) {
		this.selected=false;
		this.item=item;
	}
	public CommandOption(Object item, boolean selected) {
		this.selected=selected;
		this.item=item;
	}
}
