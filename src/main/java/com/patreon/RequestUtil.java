package com.patreon;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import static com.patreon.PatreonAPI.BASE_URI;

/**
 * A utility class for making requests to the Patreon server.  May be mocked for testing.
 */
class RequestUtil {
  private String version;

  RequestUtil() {
    try {
      InputStream resourceAsStream = this.getClass().getResourceAsStream("/version.properties");
      Properties prop = new Properties();
      prop.load(resourceAsStream);
      version = prop.getProperty("version");
    } catch (IOException ioe) {
      ioe.printStackTrace();
      version = "unknown"; // This should not be a fatal exception
    }
  }

  InputStream request(String pathSuffix, String accessToken) throws IOException {
    String prefix = BASE_URI + "/api/oauth2/api/";
    URL url = new URL(prefix.concat(pathSuffix));
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestProperty("Authorization", "Bearer ".concat(accessToken));
    connection.setRequestProperty("User-Agent",
      String.format(
        "Patreon-Java, version %s, platform %s %s",
        version,
        System.getProperty("os.name"),
        System.getProperty("os.version")));
    return connection.getInputStream();
  }
}
