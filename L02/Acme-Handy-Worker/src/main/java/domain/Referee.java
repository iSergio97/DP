package domain;

import java.util.List;

public class Referee extends Actor{
	List<Report> reports;

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
}
