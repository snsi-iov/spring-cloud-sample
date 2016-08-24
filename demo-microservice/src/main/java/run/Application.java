
package run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class Application {

	@Autowired
	private Environment environment;

	//ex: http://localhost:8080/?q=info.component
	@RequestMapping("/")
	public String query(@RequestParam("q") String q) {
        System.out.println("holooooooooooooooooo");
		return environment.getProperty(q);
	}

	@RequestMapping("/go")
	public String query(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
		
        System.out.println("goooooo "+a +"  gooooo "+ b);
		return "goooooo "+a +"  gooooo "+ b;
	}
	
	/**
	 * 使用Dspring.profiles.active來啟用profile, ex: 
	 * java -jar XXXXXX.jar -Dserver.port=0 -Dspring.profiles.active=development
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
