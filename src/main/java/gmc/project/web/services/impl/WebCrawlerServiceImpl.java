package gmc.project.web.services.impl;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmc.project.web.config.InternshalaConfigs;
import gmc.project.web.services.WebCrawlerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WebCrawlerServiceImpl implements WebCrawlerService {

	@Autowired
	public InternshalaConfigs internshalaConfigs;

	@Autowired
	public WebDriver driver;

	@Override
	public Boolean loginInternshala() {
		driver.get(internshalaConfigs.getUrl());
		WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"header\"]/div/nav/div[3]/ul/li[4]/button"));
		loginButton.click();

		WebElement usernameField = driver.findElement(By.xpath("//*[@id=\"modal_email\"]"));
		usernameField.sendKeys(internshalaConfigs.getUsername());

		WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"modal_password\"]"));
		passwordField.sendKeys(internshalaConfigs.getPassword());

		WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"modal_login_submit\"]"));
		submitButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header\"]/div/nav/div[3]/ul/li[6]/a")));

		return true;
	}
	
	private Boolean gotToResumePage() {
		Actions hoverProfile = new Actions(driver);
		WebElement profileButton = driver.findElement(By.xpath("//*[@id=\"header\"]/div/nav/div[3]/ul/li[6]/a"));
		hoverProfile.moveToElement(profileButton).perform();
		
		WebElement editResume = driver.findElement(By.xpath("//*[@id=\"profile-dropdown\"]/div/div/div/ul/div/li[2]/a"));
		hoverProfile.moveToElement(editResume).click().perform();
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"resume-container\"]")));
	
		return true;
	}

	@Override
	public Boolean addPositionInInternshala(String role) {
		gotToResumePage();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement addButton = driver.findElement(By.xpath("//*[@id=\"por-resume\"]"));
		addButton.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"other_experiences_por_description\"]")));		
		WebElement textArea = driver.findElement(By.xpath("//*[@id=\"other_experiences_por_description\"]"));
		textArea.sendKeys(role);
		
		WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"por-submit\"]"));
		saveButton.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"resume-position-of-responsibility\"]/div[2]/div[1]")));
		driver.get(driver.getCurrentUrl());
		WebElement added = driver.findElement(By.xpath("//*[@id=\"resume-position-of-responsibility\"]/div[2]/div[1]"));
		String addedRoles = added.getText();
		
		log.error(addedRoles);
		if(addedRoles.contains(role)) {
			return true;
		} else {
			throw new RuntimeException("Not Added...");
		}
		
	}

	@Override
	public WebElement getSourceCodeOfPage(String url) {
		driver.get(url);
		WebElement returnValue = driver.findElement(By.tagName("body"));
		return returnValue;
	}

	@Override
	public WebElement getResume() {
		gotToResumePage();
		WebElement returnValue = driver.findElement(By.xpath("//*[@id=\"resume-container\"]"));
		return returnValue;
	}

}
