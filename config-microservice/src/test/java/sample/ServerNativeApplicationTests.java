package sample;

import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.config.server.test.ConfigServerTestUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest({"server.port:0", "spring.application.name:bad"})
@WebAppConfiguration
public class ServerNativeApplicationTests {

	private static int configPort = 0;

	@Autowired
	private ConfigurableEnvironment environment;

	@Value("${local.server.port}")
	private int port;

	private static ConfigurableApplicationContext server;

	@BeforeClass
	public static void startConfigServer() throws IOException {
		String repo = ConfigServerTestUtils.prepareLocalRepo();
		System.out.println("repo="+repo);
		server = SpringApplication.run(
				org.springframework.cloud.config.server.ConfigServerApplication.class,
				"--server.port=" + configPort, "--spring.config.name=server",
				"--spring.cloud.config.server.git.uri=" + repo, "--spring.profiles.active=native");
		configPort = ((EmbeddedWebApplicationContext) server)
				.getEmbeddedServletContainer().getPort();
		System.setProperty("config.port", "" + configPort);
        Assert.assertNotEquals(0, configPort);
	}

	@AfterClass
	public static void close() {
		System.clearProperty("config.port");
		if (server!=null) {
			server.close();
		}
	}

	@Test
	public void contextLoads() {
		// The remote config was bad so there is no bootstrap
		assertFalse(this.environment.getPropertySources().contains("bootstrap"));
	}

	public static void main(String[] args) throws IOException {
		configPort = 8888;
		startConfigServer();      //http://localhost:8888/bad/default
//		SpringApplication.run(Application.class, args);
	}

}
