package configserver.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.config.server.test.ConfigServerTestUtils;
import org.springframework.context.ConfigurableApplicationContext;

import run.Application;

/**
 * 把測試用的config檔放到專案下的src/test/resources/config-repo內
 * 範例請見 run.ApplicationTests
 * @author WhyLu
 *
 */
public class RunWithConfigServerAbst {
	private static final Logger logger = LoggerFactory.getLogger(RunWithConfigServerAbst.class);
    public static String REPO ;

    private static ConfigurableApplicationContext server;

    protected static String startConfigServer_(String activeProfiles) throws IOException {
    	return startConfigServer_("0", activeProfiles);
    }
    /**
     * return exposed port
     * @param port 
     * @return
     * @throws IOException
     */
//    @BeforeClass
    protected static String startConfigServer_(String port, String activeProfiles) throws IOException {
    	REPO = ConfigServerTestUtils.prepareLocalRepo();
    	logger.info("ConfigServer local repo = {}", REPO);
        server = SpringApplication.run(Application.class, "--server.port="+port,
        				"--spring.application.name=configServer", 
		        		"--spring.cloud.config.enabled=true",
		        		"--spring.profiles.active="+activeProfiles,
						"--spring.cloud.config.server.git.uri=" + REPO);
        return server.getEnvironment().getProperty("local.server.port");
    }
    
    
    
    @AfterClass
    public static void close() throws IOException {
        cleanRepo();
        if (server != null) {
            server.close();
        }
    }
    public static void cleanRepo() throws IOException {
        File repoF = new File(REPO.replaceFirst("file:", ""));
        if(repoF.exists()) {
            FileUtils.forceDelete(repoF);
        }
    }
    

}
