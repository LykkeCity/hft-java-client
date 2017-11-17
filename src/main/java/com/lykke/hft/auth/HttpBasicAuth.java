package com.lykke.hft.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;

/**
 * An interceptor that adds the request header needed to use HTTP basic authentication.
 *
 * @author niau
 * @version $Id: $Id
 */
public class HttpBasicAuth implements RequestInterceptor {

    private String username;
    private String password;
    
    /**
     * <p>Getter for the field <code>username</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getUsername() {
        return username;
    }

    /**
     * <p>Setter for the field <code>username</code>.</p>
     *
     * @param username a {@link java.lang.String} object.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * <p>Getter for the field <code>password</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPassword() {
        return password;
    }

    /**
     * <p>Setter for the field <code>password</code>.</p>
     *
     * @param password a {@link java.lang.String} object.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * <p>setCredentials.</p>
     *
     * @param username a {@link java.lang.String} object.
     * @param password a {@link java.lang.String} object.
     */
    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

  /** {@inheritDoc} */
  @Override
  public void apply(RequestTemplate template) {
      RequestInterceptor requestInterceptor = new BasicAuthRequestInterceptor(username, password);
      requestInterceptor.apply(template);
  }
}
