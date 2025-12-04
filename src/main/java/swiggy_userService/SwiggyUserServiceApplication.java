package swiggy_userService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SwiggyUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwiggyUserServiceApplication.class, args);
	}

}
