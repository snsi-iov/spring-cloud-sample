package run;

import java.io.IOException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.TestRestTemplate;

import configserver.utils.RunWithConfigServerAbst;

public class ApplicationTests extends RunWithConfigServerAbst {

	static String configServerPort;

	@Test
	public void contextLoads() throws JSONException {
		String configg = new TestRestTemplate().getForObject("http://localhost:"+configServerPort+"/foobar-development.json", String.class);
		JSONObject json = new JSONObject(configg);
		Assert.assertTrue(json.getJSONObject("foo").getString("bar").equals("dev"));
		Assert.assertFalse(json.has("foo2"));
	}

	public static void main(String[] args) throws IOException {
	    startConfigServer();
	}

    
    @BeforeClass
    public static void startConfigServer() throws IOException {
        configServerPort = startConfigServer_("development");
    }

}
