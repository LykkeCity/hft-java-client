package com.lykke.hft.auth;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.oltu.oauth2.client.HttpClient;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.AuthenticationRequestBuilder;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;
import org.apache.oltu.oauth2.client.response.OAuthClientResponse;
import org.apache.oltu.oauth2.client.response.OAuthClientResponseFactory;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.token.BasicOAuthToken;

import feign.Client;
import feign.Request.Options;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Response;
import feign.RetryableException;
import feign.Util;
import com.lykke.hft.StringUtil;


/**
 * <p>OAuth class.</p>
 *
 * @author niau
 * @version $Id: $Id
 */
public class OAuth implements RequestInterceptor {

    static final int MILLIS_PER_SECOND = 1000;

    public interface AccessTokenListener {
        void notify(BasicOAuthToken token);
    }

    private volatile String accessToken;
    private Long expirationTimeMillis;
    private OAuthClient oauthClient;
    private TokenRequestBuilder tokenRequestBuilder;
    private AuthenticationRequestBuilder authenticationRequestBuilder;
    private AccessTokenListener accessTokenListener;

    /**
     * <p>Constructor for OAuth.</p>
     *
     * @param client a {@link feign.Client} object.
     * @param requestBuilder a {@link org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder} object.
     */
    public OAuth(Client client, TokenRequestBuilder requestBuilder) {
        this.oauthClient = new OAuthClient(new OAuthFeignClient(client));
        this.tokenRequestBuilder = requestBuilder;
    }

    /**
     * <p>Constructor for OAuth.</p>
     *
     * @param client a {@link feign.Client} object.
     * @param flow a {@link com.lykke.hft.auth.OAuthFlow} object.
     * @param authorizationUrl a {@link java.lang.String} object.
     * @param tokenUrl a {@link java.lang.String} object.
     * @param scopes a {@link java.lang.String} object.
     */
    public OAuth(Client client, OAuthFlow flow, String authorizationUrl, String tokenUrl, String scopes) {
        this(client, OAuthClientRequest.tokenLocation(tokenUrl).setScope(scopes));

        switch(flow) {
        case accessCode:
        case implicit:
            tokenRequestBuilder.setGrantType(GrantType.AUTHORIZATION_CODE);
            break;
        case password:
            tokenRequestBuilder.setGrantType(GrantType.PASSWORD);
            break;
        case application:
            tokenRequestBuilder.setGrantType(GrantType.CLIENT_CREDENTIALS);
            break;
        default:
            break;
        }
        authenticationRequestBuilder = OAuthClientRequest.authorizationLocation(authorizationUrl);
    }

    /**
     * <p>Constructor for OAuth.</p>
     *
     * @param flow a {@link com.lykke.hft.auth.OAuthFlow} object.
     * @param authorizationUrl a {@link java.lang.String} object.
     * @param tokenUrl a {@link java.lang.String} object.
     * @param scopes a {@link java.lang.String} object.
     */
    public OAuth(OAuthFlow flow, String authorizationUrl, String tokenUrl, String scopes) {
        this(new Client.Default(null, null), flow, authorizationUrl, tokenUrl, scopes);
    }

    /** {@inheritDoc} */
    @Override
    public void apply(RequestTemplate template) {
        // If the request already have an authorization (eg. Basic auth), do nothing
        if (template.headers().containsKey("Authorization")) {
            return;
        }
        // If first time, get the token
        if (expirationTimeMillis == null || System.currentTimeMillis() >= expirationTimeMillis) {
            updateAccessToken();
        }
        if (getAccessToken() != null) {
            template.header("Authorization", "Bearer " + getAccessToken());
        }
    }

    /**
     * <p>updateAccessToken.</p>
     */
    public synchronized void updateAccessToken() {
        OAuthJSONAccessTokenResponse accessTokenResponse;
        try {
            accessTokenResponse = oauthClient.accessToken(tokenRequestBuilder.buildBodyMessage());
        } catch (Exception e) {
            throw new RetryableException(e.getMessage(), e,null);
        }
        if (accessTokenResponse != null && accessTokenResponse.getAccessToken() != null) {
            setAccessToken(accessTokenResponse.getAccessToken(), accessTokenResponse.getExpiresIn());
            if (accessTokenListener != null) {
                accessTokenListener.notify((BasicOAuthToken) accessTokenResponse.getOAuthToken());
            }
        }
    }

    /**
     * <p>registerAccessTokenListener.</p>
     *
     * @param accessTokenListener a {@link com.lykke.hft.auth.OAuth.AccessTokenListener} object.
     */
    public synchronized void registerAccessTokenListener(AccessTokenListener accessTokenListener) {
        this.accessTokenListener = accessTokenListener;
    }

    /**
     * <p>Getter for the field <code>accessToken</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public synchronized String getAccessToken() {
        return accessToken;
    }

    /**
     * <p>Setter for the field <code>accessToken</code>.</p>
     *
     * @param accessToken a {@link java.lang.String} object.
     * @param expiresIn a {@link java.lang.Long} object.
     */
    public synchronized void setAccessToken(String accessToken, Long expiresIn) {
        this.accessToken = accessToken;
        this.expirationTimeMillis = System.currentTimeMillis() + expiresIn * MILLIS_PER_SECOND;
    }

    /**
     * <p>Getter for the field <code>tokenRequestBuilder</code>.</p>
     *
     * @return a {@link org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder} object.
     */
    public TokenRequestBuilder getTokenRequestBuilder() {
        return tokenRequestBuilder;
    }

    /**
     * <p>Setter for the field <code>tokenRequestBuilder</code>.</p>
     *
     * @param tokenRequestBuilder a {@link org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder} object.
     */
    public void setTokenRequestBuilder(TokenRequestBuilder tokenRequestBuilder) {
        this.tokenRequestBuilder = tokenRequestBuilder;
    }

    /**
     * <p>Getter for the field <code>authenticationRequestBuilder</code>.</p>
     *
     * @return a {@link org.apache.oltu.oauth2.client.request.OAuthClientRequest.AuthenticationRequestBuilder} object.
     */
    public AuthenticationRequestBuilder getAuthenticationRequestBuilder() {
        return authenticationRequestBuilder;
    }

    /**
     * <p>Setter for the field <code>authenticationRequestBuilder</code>.</p>
     *
     * @param authenticationRequestBuilder a {@link org.apache.oltu.oauth2.client.request.OAuthClientRequest.AuthenticationRequestBuilder} object.
     */
    public void setAuthenticationRequestBuilder(AuthenticationRequestBuilder authenticationRequestBuilder) {
        this.authenticationRequestBuilder = authenticationRequestBuilder;
    }

    /**
     * <p>Getter for the field <code>oauthClient</code>.</p>
     *
     * @return a {@link org.apache.oltu.oauth2.client.OAuthClient} object.
     */
    public OAuthClient getOauthClient() {
        return oauthClient;
    }

    /**
     * <p>Setter for the field <code>oauthClient</code>.</p>
     *
     * @param oauthClient a {@link org.apache.oltu.oauth2.client.OAuthClient} object.
     */
    public void setOauthClient(OAuthClient oauthClient) {
        this.oauthClient = oauthClient;
    }

    /**
     * <p>Setter for the field <code>oauthClient</code>.</p>
     *
     * @param client a {@link feign.Client} object.
     */
    public void setOauthClient(Client client) {
        this.oauthClient = new OAuthClient( new OAuthFeignClient(client));
    }

    public static class OAuthFeignClient implements HttpClient {

        private Client client;

        public OAuthFeignClient() {
            this.client = new Client.Default(null, null);
        }

        public OAuthFeignClient(Client client) {
            this.client = client;
        }

        public <T extends OAuthClientResponse> T execute(OAuthClientRequest request, Map<String, String> headers,
                String requestMethod, Class<T> responseClass)
                        throws OAuthSystemException, OAuthProblemException {

            RequestTemplate req = new RequestTemplate()
                    .append(request.getLocationUri())
                    .method(requestMethod)
                    .body(request.getBody());

            for (Entry<String, String> entry : headers.entrySet()) {
                req.header(entry.getKey(), entry.getValue());
            }
            Response feignResponse;
            String body = "";
            try {
                feignResponse = client.execute(req.request(), new Options());
                body = Util.toString(feignResponse.body().asReader());
            } catch (IOException e) {
                throw new OAuthSystemException(e);
            }

            String contentType = null;
            Collection<String> contentTypeHeader =  feignResponse.headers().get("Content-Type");
            if(contentTypeHeader != null) {
                contentType = StringUtil.join(contentTypeHeader.toArray(new String[0]), ";");
            }

            return OAuthClientResponseFactory.createCustomResponse(
                    body,
                    contentType,
                    feignResponse.status(),
                    responseClass
            );
        }

        public void shutdown() {
            // Nothing to do here
        }
    }
}
