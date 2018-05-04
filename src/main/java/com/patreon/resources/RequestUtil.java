package com.patreon.resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.patreon.PatreonAPI.BASE_URI;

/**
 * A utility class for making requests to the Patreon server.  May be mocked for testing.
 */
public class RequestUtil {

  public InputStream request(String pathSuffix, String accessToken) throws IOException {
      String prefix = BASE_URI + "/api/oauth2/api/";
      URL url = new URL(prefix.concat(pathSuffix));
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestProperty("Authorization", "Bearer ".concat(accessToken));
      connection.setRequestProperty("User-Agent",
        String.format(
          "Patreon-Java, version %s, platform %s %s",
          getVersion(),
          System.getProperty("os.name"),
          System.getProperty("os.version")));
    return connection.getInputStream();
  }

  private String getVersion() throws IOException {
    InputStream resourceAsStream = this.getClass().getResourceAsStream("/version.properties");
    java.util.Properties prop = new java.util.Properties();
    prop.load(resourceAsStream);
    return prop.getProperty("version");
  }


}
