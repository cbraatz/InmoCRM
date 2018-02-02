package crm

class ItemNameByLanguage {
	String name;
	String plural;
	String nameForWeb;
	Language language;
	
	public ItemNameByLanguage() { }
	
	public ItemNameByLanguage(String name, String plural, Language language) {
		this.name=name;
		this.plural=plural;
		this.language=language;
	}
	public ItemNameByLanguage(String name, String nameForWeb, String plural, Language language) {
		this.name=name;
		this.nameForWeb=nameForWeb;
		this.plural=plural;
		this.language=language;
	}
}
