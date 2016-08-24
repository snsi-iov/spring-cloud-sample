package configserver.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.config.server.test.ConfigServerTestUtils;
import org.springframework.context.ConfigurableApplicationContext;

import run.Application;

/**
 * 把測試用的config檔放到src/test/resources/config-repo內
 * 範例請見 run.ApplicationTests
 * @author WhyLu
 *
 */
public class RunWithConfigServerAbst {
    public static String REPO ;

    private static ConfigurableApplicationContext server;

    protected static String startConfigServer_() throws IOException {
    	return startConfigServer_("0");
    }
    /**
     * return exposed port
     * @param port 
     * @return
     * @throws IOException
     */
//    @BeforeClass
    protected static String startConfigServer_(String port) throws IOException {
    	REPO = ConfigServerTestUtils.prepareLocalRepo();
        server = SpringApplication.run(Application.class, "--server.port="+port, 
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
