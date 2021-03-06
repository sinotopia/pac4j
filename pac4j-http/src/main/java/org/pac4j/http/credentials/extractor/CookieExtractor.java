package org.pac4j.http.credentials.extractor;

import org.pac4j.core.context.Cookie;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.TokenCredentials;
import org.pac4j.core.credentials.extractor.CredentialsExtractor;
import org.pac4j.core.util.CommonHelper;

import java.util.Collection;

/**
 * Extracts a cookie value from the request context.
 *
 * @author Misagh Moayyed
 * @since 1.8.0
 */
public class CookieExtractor implements CredentialsExtractor<TokenCredentials> {

    private final String cookieName;

    private final String clientName;

    public CookieExtractor(final String cookieName, final String clientName) {
        this.cookieName = cookieName;
        this.clientName = clientName;
    }

    @Override
    public TokenCredentials extract(final WebContext context) {
        final Collection<Cookie> col = context.getRequestCookies();
        for (final Cookie c : col) {
            if (c.getName().equals(this.cookieName)) {
                return new TokenCredentials(c.getValue(), clientName);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return CommonHelper.toString(this.getClass(), "cookieName", this.cookieName,
                "clientName", this.clientName);
    }
}
