package com.patreon;

import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.nio.charset.Charset;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Jsoup.class, Document.class, HttpConnection.class, Connection.Response.class})
public class PatreonOAuthTest extends TestCase {
  PatreonOAuth oauth = new PatreonOAuth(
                                         "a client id",
                                         "your secret",
                                         "your redirect URI"
  );

  public void testAuthorizationURL() throws Exception {
    String url = oauth.getAuthorizationURL();
    assertEquals("https://www.patreon.com/oauth2/authorize?response_type=code&client_id=a+client+id&redirect_uri=your+redirect+URI", url);
  }

  public void testGetTokens() throws Exception {
    PowerMockito.mockStatic(Jsoup.class);
    PowerMockito.when(Jsoup.connect(Mockito.anyString())).
                                                           thenAnswer((invocation) ->  {
                                                               Connection conn = PowerMockito.spy((Connection) invocation.callRealMethod());

                                                               Element element = PowerMockito.mock(Element.class);
                                                               Document document = PowerMockito.mock(Document.class);
                                                               PowerMockito.when(document.body()).thenReturn(element);
                                                               PowerMockito.when(element.text()).thenReturn(IOUtils.toString(PatreonOAuthTest.class.getResourceAsStream("/oauth/get_tokens.json"), Charset.forName("UTF-8")));
                                                               PowerMockito.doReturn(document).when(conn, "post");

                                                               Connection.Response response = PowerMockito.mock(Connection.Response.class);
                                                               PowerMockito.when(response.parse()).thenReturn(document);

                                                               PowerMockito.doReturn(response).when(conn, "execute");
                                                               return conn;
                                                           });

    PatreonOAuth.TokensResponse token = oauth.getTokens("a code");
    assertEquals("some access token", token.getAccessToken());
    assertEquals("some refresh token", token.getRefreshToken());
    assertEquals(1234, token.getExpiresIn());
    assertEquals("some token scopes", token.getScope());
    assertEquals("Bearer", token.getTokenType());
  }

  public void testRefreshTokens() throws Exception {
    PowerMockito.mockStatic(Jsoup.class);
    PowerMockito.when(Jsoup.connect(Mockito.anyString())).
                                                           thenAnswer( (invocation) -> {
                                                               Connection conn = PowerMockito.spy((Connection) invocation.callRealMethod());

                                                               Element element = PowerMockito.mock(Element.class);
                                                               Document document = PowerMockito.mock(Document.class);
                                                               PowerMockito.when(document.body()).thenReturn(element);
                                                               PowerMockito.when(element.text()).thenReturn(IOUtils.toString(PatreonOAuthTest.class.getResourceAsStream("/oauth/refresh_tokens.json"), Charset.forName("UTF-8")));
                                                               PowerMockito.doReturn(document).when(conn, "post");

                                                               Connection.Response response = PowerMockito.mock(Connection.Response.class);
                                                               PowerMockito.when(response.parse()).thenReturn(document);

                                                               PowerMockito.doReturn(response).when(conn, "execute");
                                                               return conn;
                                                           });

    PatreonOAuth.TokensResponse token = oauth.getTokens("a code");
    assertEquals("some other access token", token.getAccessToken());
    assertEquals("some other refresh token", token.getRefreshToken());
    assertEquals(2345, token.getExpiresIn());
    assertEquals("some other token scopes", token.getScope());
    assertEquals("Bearer", token.getTokenType());
  }


}
