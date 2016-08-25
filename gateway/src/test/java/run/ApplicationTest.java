package run;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.ConfigurableApplicationContext;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
//@PropertySource({"classpath:bar-development.properties", "classpath:bar-production.properties"}) //this properties should get from config server at runtime
public class ApplicationTest {

    private static ConfigurableApplicationContext server;

    private static String serverPort;
	
	@BeforeClass
	public static void startServer() throws Exception {
		server = SpringApplication.run(Application.class, "--server.port=5555",   //use random port to avoid port-confliction 
										"--spring.profiles.active=development");  //active profile 'development'
		serverPort = server.getEnvironment().getProperty("local.server.port");
	}
	
    @AfterClass
    public static void close() throws IOException {
        if (server != null) {
            server.close();
        }
    }

	public static void main(String[] args) throws Exception {
		startServer();
	}
	
//	@Test
//	public void queryTest() {
//		String response = new TestRestTemplate().getForObject("http://localhost:"+serverPort+"/?q=test.hellow", String.class);
//		Assert.assertEquals("yoooooooooooooo development", response);
//	}

}
