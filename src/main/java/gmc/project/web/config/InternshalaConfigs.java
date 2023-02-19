package gmc.project.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "internshala")
public class InternshalaConfigs {
	
	private String url;
	
	private String username;
	
	private String password;
	
}
