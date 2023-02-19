package gmc.project.web.services;

import org.openqa.selenium.WebElement;

public interface WebCrawlerService {
	public Boolean loginInternshala();
	public Boolean addPositionInInternshala(String role);
	public WebElement getSourceCodeOfPage(String url);
	public WebElement getResume();
}
