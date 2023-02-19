package gmc.project.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class InternshalaToolApplication {
	
	@Autowired
	private Environment env;

	@Bean(name = "driver")
	public WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", env.getProperty("chrome.driver.location"));
//        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(true);
		return new ChromeDriver();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(InternshalaToolApplication.class, args);
	}

}
