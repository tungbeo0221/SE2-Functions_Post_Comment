package SE2.SocialMediaProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SocialMediaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaProjectApplication.class, args);
	}

}
