package crm.enums

import java.util.List;

public enum ReportExportType {
	HTML(0, 0, "html","../images/report/html.png"),
	PDF(0, 0, "pdf","../images/report/pdf.png"),
	CSV(0, 0, "csv","../images/report/csv.png")
	private final int columnNumberLimit;
	private final int pageNumberLimit;
	private final String extension;
	private final String imagePath;
	public ReportExportType(int columnNumberLimit, int pageNumberLimit, String extension, String imagePath) {
		this.columnNumberLimit=columnNumberLimit;
		this.pageNumberLimit=pageNumberLimit;
		this.extension=extension;
		this.imagePath=imagePath;
	}
	/*public boolean getValue(){
		return this.value;
	}*/
}
