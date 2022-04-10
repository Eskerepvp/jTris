// Shoutout to that random Indian Guy : https://www.youtube.com/channel/UCLL-YGtjPgLxyZpl3xEjmHg

package com.pepperoni.jtris.views.security;

import org.springframework.security.web.savedrequest.*;

import javax.servlet.http.*;

public class CustomRequestCache extends HttpSessionRequestCache {

    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
        if(SecurityUtils.isFrameworkInternalRequest(request)) {
            super.saveRequest(request, response);
        }
    }
}
