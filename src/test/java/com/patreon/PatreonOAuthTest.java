package com.patreon;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.powermock.api.mockito.PowerMockito;
import org.mockito.Mockito;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.Charsets;
import junit.framework.Assert;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Jsoup.class, Document.class, HttpConnection.class, Connection.Response.class})
public class PatreonOAuthTest extends TestCase {
    PatreonOAuth oauth = new PatreonOAuth(
        "a client id",
        "your secret",
        "your redirect URI"
    );

    public void testGetTokens() throws Exception {
        PowerMockito.mockStatic(Jsoup.class);
        PowerMockito.when(Jsoup.connect(Mockito.anyString())).
            thenAnswer(new Answer<Connection>() {
                public Connection answer(InvocationOnMock invocation) throws Throwable {
                    Connection conn = PowerMockito.spy((Connection)invocation.callRealMethod());

                    Element element = PowerMockito.mock(Element.class);
                    Document document = PowerMockito.mock(Document.class);
                    PowerMockito.when(document.body()).thenReturn(element);
                    PowerMockito.when(element.text()).thenReturn(IOUtils.toString(PatreonOAuthTest.class.getResourceAsStream("/oauth/get_tokens.json"), Charsets.UTF_8));
                    PowerMockito.doReturn(document).when(conn, "post");

                    Connection.Response response = PowerMockito.mock(Connection.Response.class);
                    PowerMockito.when(response.parse()).thenReturn(document);

                    PowerMockito.doReturn(response).when(conn, "execute");
                    return conn;
                }
            });

        PatreonOAuth.TokensResponse token = oauth.getTokens("a code");
        Assert.assertEquals("some access token", token.getAccessToken());
        Assert.assertEquals("some refresh token", token.getRefreshToken());
        Assert.assertEquals(1234, token.getExpiresIn());
        Assert.assertEquals("some token scopes", token.getScope());
        Assert.assertEquals("Bearer", token.getTokenType());
    }

    public void testRefreshTokens() throws Exception {
        PowerMockito.mockStatic(Jsoup.class);
        PowerMockito.when(Jsoup.connect(Mockito.anyString())).
            thenAnswer(new Answer<Connection>() {
                public Connection answer(InvocationOnMock invocation) throws Throwable {
                    Connection conn = PowerMockito.spy((Connection)invocation.callRealMethod());

                    Element element = PowerMockito.mock(Element.class);
                    Document document = PowerMockito.mock(Document.class);
                    PowerMockito.when(document.body()).thenReturn(element);
                    PowerMockito.when(element.text()).thenReturn(IOUtils.toString(PatreonOAuthTest.class.getResourceAsStream("/oauth/refresh_tokens.json"), Charsets.UTF_8));
                    PowerMockito.doReturn(document).when(conn, "post");

                    Connection.Response response = PowerMockito.mock(Connection.Response.class);
                    PowerMockito.when(response.parse()).thenReturn(document);

                    PowerMockito.doReturn(response).when(conn, "execute");
                    return conn;
                }
            });

        PatreonOAuth.TokensResponse token = oauth.getTokens("a code");
        Assert.assertEquals("some other access token", token.getAccessToken());
        Assert.assertEquals("some other refresh token", token.getRefreshToken());
        Assert.assertEquals(2345, token.getExpiresIn());
        Assert.assertEquals("some other token scopes", token.getScope());
        Assert.assertEquals("Bearer", token.getTokenType());
    }


}