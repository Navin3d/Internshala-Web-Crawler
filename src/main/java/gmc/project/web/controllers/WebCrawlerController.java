package gmc.project.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.web.services.WebCrawlerService;

@RestController
@RequestMapping("/crawler")
public class WebCrawlerController {
	
	@Autowired
	private WebCrawlerService webCrawlerService;
	
	@GetMapping("/initiate")
	private String initiateProcess() {
		webCrawlerService.loginInternshala();
		webCrawlerService.addPositionInInternshala("New Role");
		return "Done!";
	}

}
