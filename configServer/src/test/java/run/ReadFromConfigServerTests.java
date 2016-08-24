package run;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import configserver.utils.RunWithConfigServerAbst;


/**
 * 示範從config-server讀取config
 * @author WhyLu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@IntegrationTest({"spring.application.name:foobar", 
   "spring.cloud.config.failFast=true", "spring.cloud.config.enabled=true", "spring.profiles.active=development", 
                            "spring.cloud.config.uri=http://localhost:65341"}) //should read config 'foobar-development.properties'
@WebAppConfiguration
public class ReadFromConfigServerTests extends RunWithConfigServerAbst {

    @Autowired
    private Environment environment; //read from config server
    
	public static String configServerPort;

	@Value("${foo.bar}")  //read from config server
	private String test;

	@Test
	public void contextLoads() {
        Assert.assertNull(this.environment.getProperty("bootstrap"));
        
	    Assert.assertNull(this.environment.getProperty("bootstrap"));
        Assert.assertEquals("45172", this.environment.getProperty("server.port"));
        Assert.assertEquals("foobar dev", this.environment.getProperty("info.component"));
        
        Assert.assertEquals("dev", this.environment.getProperty("foo.bar"));
        Assert.assertEquals("dev", test);
	}

	
    public static void main(String[] args) throws IOException {
        startConfigServer();
    }

    
    @BeforeClass
    public static void startConfigServer() throws IOException {
        configServerPort = startConfigServer_("65341");
    }
}
