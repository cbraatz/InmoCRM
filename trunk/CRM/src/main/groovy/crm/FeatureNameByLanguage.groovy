package crm

class FeatureNameByLanguage {
	String name;
	String plural;
	Language language;
	
	public FeatureNameByLanguage() { }
	
	public FeatureNameByLanguage(String name, String plural, Language language) {
		this.name=name;
		this.plural=plural;
		this.language=language;
	}

}
