package com.lykke.hft.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * <p>ApiKeyAuth class.</p>
 *
 * @author niau
 * @version $Id: $Id
 */
public class ApiKeyAuth implements RequestInterceptor {
    private final String location;
    private final String paramName;

    private String apiKey;

    /**
     * <p>Constructor for ApiKeyAuth.</p>
     *
     * @param location a {@link java.lang.String} object.
     * @param paramName a {@link java.lang.String} object.
     */
    public ApiKeyAuth(String location, String paramName) {
        this.location = location;
        this.paramName = paramName;
    }

    /**
     * <p>Getter for the field <code>location</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getLocation() {
        return location;
    }

    /**
     * <p>Getter for the field <code>paramName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * <p>Getter for the field <code>apiKey</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * <p>Setter for the field <code>apiKey</code>.</p>
     *
     * @param apiKey a {@link java.lang.String} object.
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /** {@inheritDoc} */
    @Override
    public void apply(RequestTemplate template) {
        if ("query".equals(location)) {
            template.query(paramName, apiKey);
        } else if ("header".equals(location)) {
            template.header(paramName, apiKey);
        }
    }
}
